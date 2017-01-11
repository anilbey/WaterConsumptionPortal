package it.polimi.awt.springmvc.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.SmartMeter;

/**
 * @author anil
 *
 */
public interface IConsumptionRepository {
	/**
	 * returns the smartmeter belonging to the household
	 * @param household
	 * @return
	 * returns the smartmeter object of the corresponding household
	 */
	public SmartMeter getSmartMeter(Household household);
	/**
	 * retrieves all of the households living within the same district as the parameter household
	 * @param household
	 * @return
	 * returns the list of households within the same neighbourhood
	 */
	public List<Household> getHouseholds(Household household); 
	/**
	 * Returns the consumption value of a SmartMeter between two localDates
	 * @param s
	 * stands for the SmartMeter object
	 * @param date1
	 * the first date information 
	 * @param date2
	 * the second date information
	 * @return
	 * returns the consumption value of a smartmeter between the given dates
	 */
	public Double smartMeterConsumptionBetweenDates(SmartMeter s, LocalDate date1, LocalDate date2);
	/**
	 * Returns the monthly consumption data belonging to a SmartMeter
	 * @param year
	 * represents the year information
	 * @param month
	 * represents the month information
	 * @param s
	 * stands for the SmartMeter object
	 * @return
	 * returns the consumption value
	 */
	public Double smartMeterMonthlyConsumption(int year, int month, SmartMeter s);
	/**
	 * Returns the hourly consumption data of a SmartMeter
	 * @param time
	 * the first value of the time range to be visualised
	 * @param oneHourBefore
	 * the second value of the time range to be visualised
	 * @param s
	 * stands for the SmartMeter object
	 * @return
	 * returns the consumption value
	 * 
	 */
	public Double smartMeterHourlyConsumption(LocalDateTime time, LocalDateTime oneHourBefore, SmartMeter s);
	/**
	 * Returns the total consumption data recorded on a SmartMeter
	 * @param s
	 * stands for the SmartMeterObject
	 * @return
	 * returns the total consumption of a SmartMeter
	 */
	public Double smartMeterTotalConsumption(SmartMeter s);
	/**
	 * Returns the date of the first SmartMeter entry
	 * @param s
	 * stands for the SmartMeterObject
	 * @return
	 * returns the first date of entry belonging to the SmartMeter
	 * 
	 */
	public String smartMeterFirstEntryDate(SmartMeter s);
	/**
	 * Returns the date of the last SmartMeter entry
	 * @param s
	 * stands for the SmartMeterObject
	 * @return
	 * returns the last date of entry belonging to the SmartMeter
	 */
	public String smartMeterLastEntryDate(SmartMeter s);
	
}
