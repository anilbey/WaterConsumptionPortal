package it.polimi.awt.springmvc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the smart_meter database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@Table(name="smart_meter")
@NamedQuery(name="SmartMeter.findAll", query="SELECT s FROM SmartMeter s")
public class SmartMeter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer oid;

	@Column(name="building_oid")
	private Integer buildingOid;

	
	@Column(name="smart_meter_id")
	private String smartMeterId;


	public SmartMeter() {
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
	public Integer getBuildingOid() {
		return buildingOid;
	}

	/**
	 * @param buildingOid
	 */
	public void setBuildingOid(Integer buildingOid) {
		this.buildingOid = buildingOid;
	}

	/**
	 * @return
	 */
	public String getSmartMeterId() {
		return smartMeterId;
	}

	/**
	 * @param smartMeterId
	 */
	public void setSmartMeterId(String smartMeterId) {
		this.smartMeterId = smartMeterId;
	}





}