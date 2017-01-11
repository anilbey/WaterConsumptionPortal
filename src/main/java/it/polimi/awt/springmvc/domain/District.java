package it.polimi.awt.springmvc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the district database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@NamedQuery(name="District.findAll", query="SELECT d FROM District d")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer oid;

	private String city;

	private String country;

	private String name;

	private String zipcode;


	public District() {
	}

	/**
	 * @return
	 */
	public Integer getOid() {
		return this.oid;
	}

	/**
	 * @param oid
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 * @param zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}