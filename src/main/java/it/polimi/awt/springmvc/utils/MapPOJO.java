package it.polimi.awt.springmvc.utils;

/**
 * @author anil
 *
 */
public class MapPOJO {
	/**
	 * 
	 */
	private Double totalConsumption;
	/**
	 * 
	 */
	private Double currentMonthlyAverage;
	/**
	 * 
	 */
	private Double currentWeeklyAverage;
	/**
	 * 
	 */
	private Double currentDailyAverage;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private String isCommon;
	
	/**
	 * 
	 */
	public MapPOJO() {
		
	}
	
	/**
	 * @param totalConsumption
	 * @param currentMonthlyAverage
	 * @param currentWeeklyAverage
	 * @param currentDailyAverage
	 * @param address
	 * @param isCommon
	 */
	public MapPOJO(Double totalConsumption, Double currentMonthlyAverage, Double currentWeeklyAverage,
			Double currentDailyAverage, String address, String isCommon) {
		super();
		this.totalConsumption = totalConsumption;
		this.currentMonthlyAverage = currentMonthlyAverage;
		this.currentWeeklyAverage = currentWeeklyAverage;
		this.currentDailyAverage = currentDailyAverage;
		this.address = address;
		this.isCommon = isCommon;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return
	 */
	public String getIsCommon() {
		return isCommon;
	}
	/**
	 * @param isCommon
	 */
	public void setIsCommon(String isCommon) {
		this.isCommon = isCommon;
	}
	/**
	 * @return
	 */
	public Double getTotalConsumption() {
		return totalConsumption;
	}
	/**
	 * @param totalConsumption
	 */
	public void setTotalConsumption(Double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	/**
	 * @return
	 */
	public Double getCurrentMonthlyAverage() {
		return currentMonthlyAverage;
	}
	/**
	 * @param currentMonthlyAverage
	 */
	public void setCurrentMonthlyAverage(Double currentMonthlyAverage) {
		this.currentMonthlyAverage = currentMonthlyAverage;
	}
	/**
	 * @return
	 */
	public Double getCurrentWeeklyAverage() {
		return currentWeeklyAverage;
	}
	/**
	 * @param currentWeeklyAverage
	 */
	public void setCurrentWeeklyAverage(Double currentWeeklyAverage) {
		this.currentWeeklyAverage = currentWeeklyAverage;
	}
	/**
	 * @return
	 */
	public Double getCurrentDailyAverage() {
		return currentDailyAverage;
	}
	/**
	 * @param currentDailyAverage
	 */
	public void setCurrentDailyAverage(Double currentDailyAverage) {
		this.currentDailyAverage = currentDailyAverage;
	}
	
	
}
