package it.polimi.awt.springmvc.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.SmartMeter;

/**
 * @author anil
 *
 */
@Repository
public class ConsumptionRepository implements IConsumptionRepository {

	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#getHouseholds(it.polimi.awt.springmvc.domain.Household)
	 */
	public List<Household> getHouseholds(Household household) {

		try {
			TypedQuery<Household> query = em
					.createQuery("SELECT h FROM Household h where h.building.districtOid = :districtOid", Household.class);
			query.setParameter("districtOid", household.getBuilding().getDistrictOid());
			List<Household> households = query.getResultList();
			return households;
		} catch (Exception e) {
			
			return new ArrayList<Household>();
		}
		

	}

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#getSmartMeter(it.polimi.awt.springmvc.domain.Household)
	 */
	public SmartMeter getSmartMeter(Household household) {
		try {
			TypedQuery<SmartMeter> query = em.createQuery("SELECT s FROM SmartMeter s where  s.oid = :smartMeterOid",
					SmartMeter.class);
			query.setParameter("smartMeterOid", household.getSmartMeterOid());
			return query.getResultList().get(0);
		} catch (Exception e) {
			
			return null;
		}

	}
	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#smartMeterTotalConsumption(it.polimi.awt.springmvc.domain.SmartMeter)
	 */
	public Double smartMeterTotalConsumption(SmartMeter s)
	{
		try {
			Query query = em.createQuery("Select max(mr.totalConsumptionAdjusted) - min(mr.totalConsumptionAdjusted) from MeterReading mr "
					+ " where mr.smartMeterOid = :smartMeterOid");
			query.setParameter("smartMeterOid", s.getOid());
			List results = query.getResultList();
			String resultString = String.valueOf(results.get(0));
			Double result = new Double(resultString);
			return result;
		} catch (NumberFormatException e) {
			
			return new Double(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#smartMeterFirstEntry(it.polimi.awt.springmvc.domain.SmartMeter)
	 */
	public String smartMeterFirstEntryDate(SmartMeter s)
	{	
			try {
				Query query = em.createQuery("Select mr.readingDateTime from MeterReading mr "
						+ " where mr.smartMeterOid = :smartMeterOid and mr.oid = (select min(mr2.oid) from MeterReading mr2 where mr2.smartMeterOid = :smartMeterOid) ");
				query.setParameter("smartMeterOid", s.getOid());
				List results = query.getResultList();
				String result = String.valueOf(results.get(0));
				return result;
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}	
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#smartMeterLastEntry(it.polimi.awt.springmvc.domain.SmartMeter)
	 */
	public String smartMeterLastEntryDate(SmartMeter s)
	{	
			try {
				Query query = em.createQuery("Select mr.readingDateTime from MeterReading mr "
						+ " where mr.smartMeterOid = :smartMeterOid and mr.oid = (select max(mr2.oid) from MeterReading mr2 where mr2.smartMeterOid = :smartMeterOid) ");
				query.setParameter("smartMeterOid", s.getOid());
				List results = query.getResultList();
				String result = String.valueOf(results.get(0));
				return result;
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}	
	}
	
	//
	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#smartMeterConsumptionBetweenDates(it.polimi.awt.springmvc.domain.SmartMeter, java.time.LocalDate, java.time.LocalDate)
	 */
	public Double smartMeterConsumptionBetweenDates(SmartMeter s, LocalDate date1, LocalDate date2) {
		Query query;
		try {
			Date d1 = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date d2 = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			query = em.createQuery("Select max(mr.totalConsumptionAdjusted) - min(mr.totalConsumptionAdjusted) "
					+ "from MeterReading mr "
					+ "where mr.smartMeterOid = :smartMeterOid and mr.readingDateTime >= :date1 and mr.readingDateTime <= :date2");
			query.setParameter("smartMeterOid", s.getOid());
			query.setParameter("date1", d1);
			query.setParameter("date2", d2);
			List results = query.getResultList();
			String resultString = String.valueOf(results.get(0));
			Double result = new Double(resultString);
			
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return new Double(0);
		}

	}

	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#smartMeterMonthlyConsumption(int, int, it.polimi.awt.springmvc.domain.SmartMeter)
	 */
	public Double smartMeterMonthlyConsumption(int year, int month, SmartMeter s) {
		try {
			Query query = em.createQuery("Select max(mr.totalConsumptionAdjusted) - min(mr.totalConsumptionAdjusted) "
					+ "from MeterReading mr where mr.smartMeterOid = :smartMeterOid "
					+ "and FUNCTION('YEAR', mr.readingDateTime) = :year and FUNCTION('MONTH', mr.readingDateTime) = :month ");

			query.setParameter("year", year);
			query.setParameter("month", month);
			query.setParameter("smartMeterOid", s.getOid());

			List results = query.getResultList();
			String resultString = String.valueOf(results.get(0));
			Double result = new Double(resultString);
			return result;

		} catch (Exception e) {
			return new Double(0);
		}
	}
	/* (non-Javadoc)
	 * @see it.polimi.awt.springmvc.repository.IConsumptionRepository#smartMeterHourlyConsumption(java.time.LocalDateTime, java.time.LocalDateTime, it.polimi.awt.springmvc.domain.SmartMeter)
	 */
	public Double smartMeterHourlyConsumption(LocalDateTime time, LocalDateTime oneHourBefore, SmartMeter s){
		
		
		
		int year1 =time.getYear();
		int month1 = time.getMonthValue();
		int day1 = time.getDayOfMonth();
		int hour1 = time.getHour();
		int year2 = oneHourBefore.getYear();
		int month2 = oneHourBefore.getMonthValue();
		int day2 = oneHourBefore.getDayOfMonth();
		int hour2 = oneHourBefore.getHour();
		
		
		
		
		try {
			Query query = em.createQuery("Select mr.totalConsumptionAdjusted - (Select mr.totalConsumptionAdjusted from MeterReading mr where mr.smartMeterOid = :smartMeterOid "
					+ " and FUNCTION('YEAR', mr.readingDateTime) = :year2 and FUNCTION('MONTH', mr.readingDateTime) = :month2 "
					+ " and FUNCTION('DAY', mr.readingDateTime) = :day2 and FUNCTION('HOUR', mr.readingDateTime) = :hour2) "
					+ "from MeterReading mr where mr.smartMeterOid = :smartMeterOid "
					+ "and FUNCTION('YEAR', mr.readingDateTime) = :year1 and FUNCTION('MONTH', mr.readingDateTime) = :month1 "
					+ "and FUNCTION('DAY', mr.readingDateTime) = :day1 and FUNCTION('HOUR', mr.readingDateTime) = :hour1");

			query.setParameter("hour1", hour1);
			query.setParameter("day1", day1);
			query.setParameter("year1", year1);
			query.setParameter("month1", month1);
			query.setParameter("smartMeterOid", s.getOid());
			query.setParameter("hour2", hour2);
			query.setParameter("day2", day2);
			query.setParameter("year2", year2);
			query.setParameter("month2", month2);

			List results = query.getResultList();
			String resultString = String.valueOf(results.get(0));
			Double result = new Double(resultString);
			return result;

		} catch (Exception e) {
			return new Double(0);
		}
	}

}
