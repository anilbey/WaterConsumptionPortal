package it.polimi.awt.springmvc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the meter_reading database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@Table(name="meter_reading")
@NamedQuery(name="MeterReading.findAll", query="SELECT m FROM MeterReading m")
public class MeterReading implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer oid;

	private String company;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reading_date_time")
	private Date readingDateTime;

	@Column(name="smart_meter_oid")
	private Integer smartMeterOid;

	@Column(name="total_consumption")
	private BigDecimal totalConsumption;

	@Column(name="total_consumption_adjusted")
	private BigDecimal totalConsumptionAdjusted;

	/**
	 * 
	 */
	public MeterReading() {
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
	public String getCompany() {
		return this.company;
	}

	/**
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return
	 */
	public Date getReadingDateTime() {
		return this.readingDateTime;
	}

	/**
	 * @param readingDateTime
	 */
	public void setReadingDateTime(Date readingDateTime) {
		this.readingDateTime = readingDateTime;
	}

	/**
	 * @return
	 */
	public Integer getSmartMeterOid() {
		return this.smartMeterOid;
	}

	/**
	 * @param smartMeterOid
	 */
	public void setSmartMeterOid(Integer smartMeterOid) {
		this.smartMeterOid = smartMeterOid;
	}

	/**
	 * @return
	 */
	public BigDecimal getTotalConsumption() {
		return this.totalConsumption;
	}

	/**
	 * @param totalConsumption
	 */
	public void setTotalConsumption(BigDecimal totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	/**
	 * @return
	 */
	public BigDecimal getTotalConsumptionAdjusted() {
		return this.totalConsumptionAdjusted;
	}

	/**
	 * @param totalConsumptionAdjusted
	 */
	public void setTotalConsumptionAdjusted(BigDecimal totalConsumptionAdjusted) {
		this.totalConsumptionAdjusted = totalConsumptionAdjusted;
	}

}