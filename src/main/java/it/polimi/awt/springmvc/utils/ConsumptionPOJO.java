package it.polimi.awt.springmvc.utils;

import java.util.LinkedList;

/**
 * @author anil
 *
 */
public class ConsumptionPOJO {

	/**
	 * 
	 */
	public ConsumptionPOJO() {
		cols = new LinkedList<Col>();
		rows = new LinkedList<Row>();
	}

	/**
	 * 
	 */
	private LinkedList<Col> cols;

	/**
	 * 
	 */
	private LinkedList<Row> rows;

	/**
	 * @return
	 */
	public LinkedList<Col> getCols() {
		return cols;
	}

	/**
	 * 
	 */
	public void addColumnValues() {

		cols.add(new Col("date", "Day", "string"));
		cols.add(new Col("value", "Water consumption", "number"));
		cols.add(new Col("avgUser", "Your average", "number"));
		cols.add(new Col("avgNeig", "Neighbourhood average", "number"));

	}
	
	/**
	 * 
	 */
	public void addHourlyColumnValues() {

		cols.add(new Col("hour", "Hour", "string"));
		cols.add(new Col("value", "Water consumption", "number"));
		
	}

	/**
	 * @return
	 */
	public LinkedList<Row> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 */
	public void setRows(LinkedList<Row> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassPojo [cols = " + cols + ", rows = " + rows + "]";
	}
}
