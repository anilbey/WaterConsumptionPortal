package it.polimi.awt.springmvc.service;

import java.util.List;

import it.polimi.awt.springmvc.domain.Household;
import it.polimi.awt.springmvc.domain.SmartMeter;

/**
 * @author anil
 *
 */
public interface IConsumptionService {

	/**
	 * Retrieves the household consumption data as a JSON String
	 * @param household
	 * parameter stores the household data
	 * @param date1
	 * represents the value for the first date entered
	 * @param date2
	 * represents the value for the second date entered
	 * @param viewType
	 * represents the view granularity
	 * @param households
	 * list of households in the neighbourhood
	 * @param neighbourhoodSmartMeters
	 * list of smartmeters within the neighbourhood
	 * @param householdSmartMeter
	 * the smartmeter object
	 * @return 
	 * returns the consumption data as the JSON string
	 */
	public String getHouseholdConsumption(Household household, String date1, String date2, String viewType,
			List<Household> households, List<SmartMeter> neighbourhoodSmartMeters, SmartMeter householdSmartMeter);

	/**
	 * Retrieves the hourly consumption data as a JSON String
	 * @param household
	 * the household object
	 * @param date
	 * the date needed for the hourly view
	 * @return
	 * returns the hourly consumption data as a JSON string
	 */
	public String getHourlyConsumption(Household household, String date);

	/**
	 * Retrieves the list of households living within the same district as the parameter Household
	 * @param household
	 * the household object
	 * @return
	 * returns the list of households within the same neighbourhood
	 */
	public List<Household> getHouseholds(Household household);
	
	/**
	 * Retrieves the list of SmartMeters belonging to the neighbours
	 * @param households
	 * the household object
	 * @return
	 * returns all of the smartmeters within the neighbourhood
	 */
	public List<SmartMeter> getNeighbourhoodSmartMeters(List<Household> households);
	
	/**
	 * Returns the SmartMeter of a household
	 * @param household
	 * the house object
	 * @return
	 * returns the smartmeter belonging to corresponding household object
	 */
	public SmartMeter  getSmartMeter(Household household);

}
