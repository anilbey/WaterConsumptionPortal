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
 * The persistent class for the household database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@NamedQuery(name="Household.findAll", query="SELECT h FROM Household h")
public class Household implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer oid;
	
	//bi-directional many-to-one association to Building
	@ManyToOne
	@JoinColumn(name="building_oid", insertable = false, updatable = false)
	private Building building;

	@Column(name="building_oid")
	private Integer buildingOid;
	

	@Column(name="children0_4")
	private Integer children04;

	@Column(name="children5_9")
	private Integer children59;

	private Integer children9;

	@Column(name="environmental_attitude")
	private String environmentalAttitude;

	@Column(name="family_id")
	private String familyId;

	@Column(name="household_garden")
	private Boolean householdGarden;

	@Column(name="household_garden_area")
	private BigDecimal householdGardenArea;

	@Column(name="household_pool")
	private Boolean householdPool;

	@Column(name="household_pool_volume")
	private BigDecimal householdPoolVolume;

	@Column(name="household_size")
	private BigDecimal householdSize;

	@Column(name="irrigation_system")
	private Boolean irrigationSystem;

	@Column(name="number_adults")
	private Integer numberAdults;

	@Column(name="number_bathrooms")
	private String numberBathrooms;

	@Column(name="number_pets")
	private Integer numberPets;

	private Boolean ownership;

	@Column(name="public")
	private Boolean public_;

	@Column(name="residency_type")
	private String residencyType;

	private Boolean second;

	@Column(name="smart_meter_oid")
	private Integer smartMeterOid;

	private Boolean visible;

	/**
	 * 
	 */
	public Household() {
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
	public Building getBuilding() {
		return building;
	}

	/**
	 * @param building
	 */
	public void setBuilding(Building building) {
		this.building = building;
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
	public Integer getChildren04() {
		return children04;
	}

	/**
	 * @param children04
	 */
	public void setChildren04(Integer children04) {
		this.children04 = children04;
	}

	/**
	 * @return
	 */
	public Integer getChildren59() {
		return children59;
	}

	/**
	 * @param children59
	 */
	public void setChildren59(Integer children59) {
		this.children59 = children59;
	}

	/**
	 * @return
	 */
	public Integer getChildren9() {
		return children9;
	}

	/**
	 * @param children9
	 */
	public void setChildren9(Integer children9) {
		this.children9 = children9;
	}

	/**
	 * @return
	 */
	public String getEnvironmentalAttitude() {
		return environmentalAttitude;
	}

	/**
	 * @param environmentalAttitude
	 */
	public void setEnvironmentalAttitude(String environmentalAttitude) {
		this.environmentalAttitude = environmentalAttitude;
	}

	/**
	 * @return
	 */
	public String getFamilyId() {
		return familyId;
	}

	/**
	 * @param familyId
	 */
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	/**
	 * @return
	 */
	public Boolean getHouseholdGarden() {
		return householdGarden;
	}

	/**
	 * @param householdGarden
	 */
	public void setHouseholdGarden(Boolean householdGarden) {
		this.householdGarden = householdGarden;
	}

	/**
	 * @return
	 */
	public BigDecimal getHouseholdGardenArea() {
		return householdGardenArea;
	}

	/**
	 * @param householdGardenArea
	 */
	public void setHouseholdGardenArea(BigDecimal householdGardenArea) {
		this.householdGardenArea = householdGardenArea;
	}

	/**
	 * @return
	 */
	public Boolean getHouseholdPool() {
		return householdPool;
	}

	/**
	 * @param householdPool
	 */
	public void setHouseholdPool(Boolean householdPool) {
		this.householdPool = householdPool;
	}

	/**
	 * @return
	 */
	public BigDecimal getHouseholdPoolVolume() {
		return householdPoolVolume;
	}

	/**
	 * @param householdPoolVolume
	 */
	public void setHouseholdPoolVolume(BigDecimal householdPoolVolume) {
		this.householdPoolVolume = householdPoolVolume;
	}

	/**
	 * @return
	 */
	public BigDecimal getHouseholdSize() {
		return householdSize;
	}

	/**
	 * @param householdSize
	 */
	public void setHouseholdSize(BigDecimal householdSize) {
		this.householdSize = householdSize;
	}

	/**
	 * @return
	 */
	public Boolean getIrrigationSystem() {
		return irrigationSystem;
	}

	/**
	 * @param irrigationSystem
	 */
	public void setIrrigationSystem(Boolean irrigationSystem) {
		this.irrigationSystem = irrigationSystem;
	}

	/**
	 * @return
	 */
	public Integer getNumberAdults() {
		return numberAdults;
	}

	/**
	 * @param numberAdults
	 */
	public void setNumberAdults(Integer numberAdults) {
		this.numberAdults = numberAdults;
	}

	/**
	 * @return
	 */
	public String getNumberBathrooms() {
		return numberBathrooms;
	}

	/**
	 * @param numberBathrooms
	 */
	public void setNumberBathrooms(String numberBathrooms) {
		this.numberBathrooms = numberBathrooms;
	}

	/**
	 * @return
	 */
	public Integer getNumberPets() {
		return numberPets;
	}

	/**
	 * @param numberPets
	 */
	public void setNumberPets(Integer numberPets) {
		this.numberPets = numberPets;
	}

	/**
	 * @return
	 */
	public Boolean getOwnership() {
		return ownership;
	}

	/**
	 * @param ownership
	 */
	public void setOwnership(Boolean ownership) {
		this.ownership = ownership;
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
	public String getResidencyType() {
		return residencyType;
	}

	/**
	 * @param residencyType
	 */
	public void setResidencyType(String residencyType) {
		this.residencyType = residencyType;
	}

	/**
	 * @return
	 */
	public Boolean getSecond() {
		return second;
	}

	/**
	 * @param second
	 */
	public void setSecond(Boolean second) {
		this.second = second;
	}

	/**
	 * @return
	 */
	public Integer getSmartMeterOid() {
		return smartMeterOid;
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
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * @param visible
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	



	

}