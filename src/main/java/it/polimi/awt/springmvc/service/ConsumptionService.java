package it.polimi.awt.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.SmartMeter;
import it.polimi.awt.springmvc.repository.IConsumptionRepository;
import it.polimi.awt.springmvc.utils.ConsumptionPOJO;
import it.polimi.awt.springmvc.utils.Row;
import it.polimi.awt.springmvc.utils.RowValue;
import it.polimi.awt.springmvc.utils.Utilities;

/**
 * @author anil
 *
 */
@Service
@Transactional
@ComponentScan("it.polimi.awt.springmvc.repository")
public class ConsumptionService implements IConsumptionService {

	/**
	 * 
	 */
	@Autowired
	private IConsumptionRepository consumptionRepository;

	/**
	 * @param household
	 * @param start
	 * @param end
	 * @param viewType
	 * @param smartMeter
	 * @return
	 */
	private Double householdAverageConsumption(Household household, LocalDate start, LocalDate end, String viewType,
			SmartMeter smartMeter) {

		Double result = consumptionRepository.smartMeterConsumptionBetweenDates(smartMeter, start, end);
		if (viewType.equals("Month"))
			result /= (ChronoUnit.MONTHS.between(start, end)) + 1;
		else if (viewType.equals("Week"))
			result /= (ChronoUnit.WEEKS.between(start, end)) + 1;
		else if (viewType.equals("Day"))
			result /= (ChronoUnit.DAYS.between(start, end)) + 1;
		return result;
	}

	/**
	 * @param start
	 * @param end
	 * @param viewType
	 * @param households
	 * @param neighbourhoodSmartMeters
	 * @return
	 */
	private Double neighbourhoodAverageConsumption(LocalDate start, LocalDate end, String viewType,
			List<Household> households, List<SmartMeter> neighbourhoodSmartMeters) {

		Double result = new Double(0);
		for (SmartMeter smartMeter : neighbourhoodSmartMeters) {
			result += consumptionRepository.smartMeterConsumptionBetweenDates(smartMeter, start, end);
		}

		result /= households.size();
		if (viewType.equals("Month"))
			result /= (ChronoUnit.MONTHS.between(start, end)) + 1;
		else if (viewType.equals("Week"))
			result /= (ChronoUnit.WEEKS.between(start, end)) + 1;
		else if (viewType.equals("Day"))
			result /= (ChronoUnit.DAYS.between(start, end)) + 1;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.awt.springmvc.service.IConsumptionService#
	 * getHouseholdConsumption(it.polimi.awt.springmvc.domain.Household,
	 * java.lang.String, java.lang.String, java.lang.String, java.util.List,
	 * java.util.List, it.polimi.awt.springmvc.domain.SmartMeter)
	 */
	public String getHouseholdConsumption(Household household, String date1, String date2, String viewType,
			List<Household> households, List<SmartMeter> neighbourhoodSmartMeters, SmartMeter householdSmartMeter) {

		ConsumptionPOJO myPojo;

		LocalDate start = LocalDate.parse(date1), end = LocalDate.parse(date2);
		Double householdAverage = householdAverageConsumption(household, start, end, viewType, householdSmartMeter);
		Double neighbourAverage = neighbourhoodAverageConsumption(start, end, viewType, households,
				neighbourhoodSmartMeters);

		if (ChronoUnit.DAYS.between(start, end) > 0)// iterate for each month
		{
			if (viewType.equals("Month"))
				myPojo = monthlyConsumption(start, end, householdSmartMeter, householdAverage, neighbourAverage);
			else
				myPojo = dailyOrWeeklyConsumption(start, end, householdSmartMeter, householdAverage, neighbourAverage,
						viewType);

		} else {
			myPojo = new ConsumptionPOJO();
			myPojo.addColumnValues();
		}

		return Utilities.convertToJSONString(myPojo);
	}

	/**
	 * @param start
	 * @param end
	 * @param smartMeter
	 * @param householdAverage
	 * @param neighbourAverage
	 * @return
	 */
	private ConsumptionPOJO monthlyConsumption(LocalDate start, LocalDate end, SmartMeter smartMeter,
			Double householdAverage, Double neighbourAverage) {
		ConsumptionPOJO myPojo = new ConsumptionPOJO();
		myPojo.addColumnValues();
		LinkedList<Row> rows = myPojo.getRows();

		for (LocalDate date = start; date.isBefore(end); date = date.plusMonths(1)) {

			String[] parts3 = date.toString().split("-");
			String year = parts3[0];
			String month = parts3[1];
			Double value = (consumptionRepository.smartMeterMonthlyConsumption(Integer.valueOf(year),
					Integer.valueOf(month), smartMeter));

			LinkedList<RowValue> vLinkedList = new LinkedList<RowValue>();
			vLinkedList.add(new RowValue(String.valueOf(month) + "-" + String.valueOf(year)));
			vLinkedList.add(new RowValue(String.valueOf(value)));
			vLinkedList.add(new RowValue(String.valueOf(householdAverage)));
			vLinkedList.add(new RowValue(String.valueOf(neighbourAverage)));

			rows.add(new Row(vLinkedList));

		}
		myPojo.setRows(rows);
		return myPojo;
	}

	/**
	 * @param start
	 * @param end
	 * @param smartMeter
	 * @param householdAverage
	 * @param neighbourAverage
	 * @param type
	 * @return
	 */
	private ConsumptionPOJO dailyOrWeeklyConsumption(LocalDate start, LocalDate end, SmartMeter smartMeter,
			Double householdAverage, Double neighbourAverage, String type) {

		ConsumptionPOJO myPojo = new ConsumptionPOJO();
		myPojo.addColumnValues();
		LinkedList<Row> rows = myPojo.getRows();

		for (LocalDate date = start; date
				.isBefore(end); date = (type.equals("Week") ? date.plusWeeks(1) : date.plusDays(1))) {

			Double value = (consumptionRepository.smartMeterConsumptionBetweenDates(smartMeter, date,
					(type.equals("Week") ? date.plusWeeks(1) : date.plusDays(1))));

			LinkedList<RowValue> vLinkedList = new LinkedList<RowValue>();

			if (type.equals("Week")) {

				String startDay = String.valueOf(date.getDayOfMonth());

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
				String startMonth = date.format(formatter);

				String endDay = String.valueOf(date.plusWeeks(1).getDayOfMonth());
				String endMonth = date.plusWeeks(1).format(formatter);
				vLinkedList.add(new RowValue(startDay + startMonth + "-" + endDay + endMonth));
			} else {
				vLinkedList.add(new RowValue(date.toString()));
			}

			vLinkedList.add(new RowValue(String.valueOf(value)));
			vLinkedList.add(new RowValue(String.valueOf(householdAverage)));
			vLinkedList.add(new RowValue(String.valueOf(neighbourAverage)));

			rows.add(new Row(vLinkedList));

		}
		myPojo.setRows(rows);
		return myPojo;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.awt.springmvc.service.IConsumptionService#getHourlyConsumption(
	 * it.polimi.awt.springmvc.domain.Household, java.lang.String)
	 */
	public String getHourlyConsumption(Household household, String date) {

		LocalDate lDate = LocalDate.parse(date);
		LocalDateTime ldt = LocalDateTime.of(lDate, LocalTime.MIN); // lDate and
																	// 00:00:00
		SmartMeter smartMeter = consumptionRepository.getSmartMeter(household);

		ConsumptionPOJO myPojo = new ConsumptionPOJO();
		myPojo.addHourlyColumnValues();
		LinkedList<Row> rows = myPojo.getRows();

		for (int i = 0; i < 24; i++) {
			Double value = new Double(0);
			LocalDateTime oneHourBefore = ldt.minusHours(1);

			value += consumptionRepository.smartMeterHourlyConsumption(ldt, oneHourBefore, smartMeter);

			LinkedList<RowValue> vLinkedList = new LinkedList<RowValue>();
			vLinkedList.add(new RowValue(((i < 10) ? "0" : "") + String.valueOf(i) + ":00"));
			vLinkedList.add(new RowValue(String.valueOf(value)));
			rows.add(new Row(vLinkedList));
			ldt = ldt.plusHours(1);
		}
		myPojo.setRows(rows);

		return Utilities.convertToJSONString(myPojo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.awt.springmvc.service.IConsumptionService#getHouseholds(it.
	 * polimi.awt.springmvc.domain.Household)
	 */
	
	public List<Household> getHouseholds(Household household) {
		// TODO Auto-generated method stub
		return consumptionRepository.getHouseholds(household);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.awt.springmvc.service.IConsumptionService#
	 * getNeighbourhoodSmartMeters(java.util.List)
	 */
	
	public List<SmartMeter> getNeighbourhoodSmartMeters(List<Household> households) {
		// TODO Auto-generated method stub
		ArrayList<SmartMeter> smartMeters = new ArrayList<SmartMeter>();
		for (Household household : households) {
			smartMeters.add(consumptionRepository.getSmartMeter(household));
		}

		return smartMeters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.awt.springmvc.service.IConsumptionService#getSmartMeter(it.
	 * polimi.awt.springmvc.domain.Household)
	 */
	
	public SmartMeter getSmartMeter(Household household) {
		// TODO Auto-generated method stub
		return consumptionRepository.getSmartMeter(household);
	}

}
