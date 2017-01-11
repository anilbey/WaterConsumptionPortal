package it.polimi.awt.springmvc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the building database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@NamedQuery(name="Building.findAll", query="SELECT b FROM Building b")
public class Building implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer oid;

	private String address;

	private Integer age;

	@Column(name="building_size")
	private BigDecimal buildingSize;

	@Column(name="district_oid")
	private Integer districtOid;

	//bi-directional many-to-one association to Household
	@ManyToOne
	@JoinColumn(name="district_oid", insertable = false, updatable = false)
	private District district;
	
	
	public Building() {
	}

	/**
	 * @return
	 */
	public Integer getOid() {
		return oid;
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
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return
	 */
	public BigDecimal getBuildingSize() {
		return buildingSize;
	}

	/**
	 * @param buildingSize
	 */
	public void setBuildingSize(BigDecimal buildingSize) {
		this.buildingSize = buildingSize;
	}

	/**
	 * @return
	 */
	public Integer getDistrictOid() {
		return districtOid;
	}

	/**
	 * @param districtOid
	 */
	public void setDistrictOid(Integer districtOid) {
		this.districtOid = districtOid;
	}

	/**
	 * @return
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}



}