package org.obsplatform.test.common.services;

public class ServiceDomain {

	private Long id;
	private String serviceCode;
	private String serviceDescription;
	private String serviceType;
	private String serviceStatus;
	private String serviceUnitType;
	private String isOptional;
	private String isAutoProvision;

	
	public ServiceDomain(){
		//Jackson uses default constructor to create the instances of java class using reflection. 
		// If default constructor is not provided, then you will get JsonMappingException in runtime.
	}
	
	public Long getId() {
		return id;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getServiceUnitType() {
		return serviceUnitType;
	}

	public void setServiceUnitType(String serviceUnitType) {
		this.serviceUnitType = serviceUnitType;
	}

	public String getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}

	public String getIsAutoProvision() {
		return isAutoProvision;
	}

	public void setIsAutoProvision(String isAutoProvision) {
		this.isAutoProvision = isAutoProvision;
	}

	public void setId(Long id) {
		this.id = id;
	}

}