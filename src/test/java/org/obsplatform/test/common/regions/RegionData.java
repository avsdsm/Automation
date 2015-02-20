package org.obsplatform.test.common.regions;

public class RegionData {

	private Long id;
	private Long regionId;
	private Long stateId;
	private Long countryId;
	private String stateName;
	private char isDefault;

	public Long getId() {
		return id;
	}

	public Long getRegionId() {
		return regionId;
	}

	public Long getStateId() {
		return stateId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public String getStateName() {
		return stateName;
	}

	public char getIsDefault() {
		return isDefault;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setIsDefault(char isDefault) {
		this.isDefault = isDefault;
	}

}
