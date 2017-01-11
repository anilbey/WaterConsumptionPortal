package it.polimi.awt.springmvc.utils;

/**
 * @author anil
 *
 */
public class Col {

	/**
	 * @param id
	 * @param label
	 * @param type
	 */
	public Col(String id, String label, String type) {
		super();
		this.id = id;
		this.label = label;
		this.type = type;
	}

	/**
	 * 
	 */
	private String id;

	/**
	 * 
	 */
	private String label;

	/**
	 * 
	 */
	private String type;

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", label = " + label + ", type = " + type + "]";
	}
}