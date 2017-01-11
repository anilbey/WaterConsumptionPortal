package it.polimi.awt.springmvc.utils;

import java.util.LinkedList;

/**
 * @author anil
 *
 */
public class Row {
	/**
	 * 
	 */
	private LinkedList<RowValue> c;

	/**
	 * @param c
	 */
	public Row(LinkedList<RowValue> c) {
		super();
		this.c = c;
	}

	/**
	 * @return
	 */
	public LinkedList<RowValue> getC() {
		return c;
	}

	/**
	 * @param c
	 */
	public void setC(LinkedList<RowValue> c) {
		this.c = c;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassPojo [c = " + c + "]";
	}
}
