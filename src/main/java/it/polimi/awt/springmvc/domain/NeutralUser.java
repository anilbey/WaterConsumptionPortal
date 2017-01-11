package it.polimi.awt.springmvc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the neutral_user database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@Table(name="neutral_user")
@NamedQuery(name="NeutralUser.findAll", query="SELECT n FROM NeutralUser n")
public class NeutralUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_oid")
	private Integer userOid;
	
	//bi-directional many-to-one association to Household
	@ManyToOne
	@JoinColumn(name="household_oid", insertable = false, updatable = false)
	private Household household;

	private String currency;

	@Column(name="educational_level")
	private String educationalLevel;

	@Column(name="family_role")
	private String familyRole;

	@Column(name="house_holder")
	private Boolean houseHolder;

	@Column(name="household_oid")
	private Integer householdOid;

	@Column(name="income_rate")
	private String incomeRate;

	private String language;

	@Column(name="length_unit")
	private String lengthUnit;

	@Column(name="public")
	private Boolean public_;

	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;

	@Column(name="temperature_unit")
	private String temperatureUnit;

	/**
	 * 
	 */
	public NeutralUser() {
	}

	/**
	 * @return
	 */
	public Integer getUserOid() {
		return userOid;
	}

	/**
	 * @param userOid
	 */
	public void setUserOid(Integer userOid) {
		this.userOid = userOid;
	}

	/**
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return
	 */
	public String getEducationalLevel() {
		return educationalLevel;
	}

	/**
	 * @param educationalLevel
	 */
	public void setEducationalLevel(String educationalLevel) {
		this.educationalLevel = educationalLevel;
	}

	/**
	 * @return
	 */
	public String getFamilyRole() {
		return familyRole;
	}

	/**
	 * @param familyRole
	 */
	public void setFamilyRole(String familyRole) {
		this.familyRole = familyRole;
	}

	/**
	 * @return
	 */
	public Boolean getHouseHolder() {
		return houseHolder;
	}

	/**
	 * @param houseHolder
	 */
	public void setHouseHolder(Boolean houseHolder) {
		this.houseHolder = houseHolder;
	}

	/**
	 * @return
	 */
	public Integer getHouseholdOid() {
		return householdOid;
	}

	/**
	 * @param householdOid
	 */
	public void setHouseholdOid(Integer householdOid) {
		this.householdOid = householdOid;
	}

	/**
	 * @return
	 */
	public String getIncomeRate() {
		return incomeRate;
	}

	/**
	 * @param incomeRate
	 */
	public void setIncomeRate(String incomeRate) {
		this.incomeRate = incomeRate;
	}

	/**
	 * @return
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return
	 */
	public String getLengthUnit() {
		return lengthUnit;
	}

	/**
	 * @param lengthUnit
	 */
	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	/**
	 * @return
	 */
	public Boolean getPublic_() {
		return public_;
	}

	/**
	 * @param public_
	 */
	public void setPublic_(Boolean public_) {
		this.public_ = public_;
	}

	/**
	 * @return
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return
	 */
	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	/**
	 * @param temperatureUnit
	 */
	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	/**
	 * @return
	 */
	public Household getHousehold() {
		return household;
	}

	/**
	 * @param household
	 */
	public void setHousehold(Household household) {
		this.household = household;
	}




}