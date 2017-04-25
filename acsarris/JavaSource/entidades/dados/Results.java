package entidades.dados;

import java.util.Date;

import util.JSFUtil;

public class Results {

	private Integer id;

	private String provisioningId;

	private String oui;

	private String model;

	private String productClass;

	private String vendor;

	private String userKey1;

	private String userKey2;

	private String userKey3;

	private String userKey4;

	private String userKey5;

	private String userKey6;

	private String ip;

	private String mac;

	private String serialNumber;

	private String firmwareFamily;

	private String softwareVersion;

	private Integer customerId;

	private double lastModified;

	private double lastContact;

	private Boolean isContacted;

	private long firstContactTime;

	private String status;

	private String population;

	private String classification;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvisioningId() {
		return provisioningId;
	}

	public void setProvisioningId(String provisioningId) {
		this.provisioningId = provisioningId;
	}

	public String getOui() {
		return oui;
	}

	public void setOui(String oui) {
		this.oui = oui;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getUserKey1() {
		return userKey1;
	}

	public void setUserKey1(String userKey1) {
		this.userKey1 = userKey1;
	}

	public String getUserKey2() {
		return userKey2;
	}

	public void setUserKey2(String userKey2) {
		this.userKey2 = userKey2;
	}

	public String getUserKey3() {
		return userKey3;
	}

	public void setUserKey3(String userKey3) {
		this.userKey3 = userKey3;
	}

	public String getUserKey4() {
		return userKey4;
	}

	public void setUserKey4(String userKey4) {
		this.userKey4 = userKey4;
	}

	public String getUserKey5() {
		return userKey5;
	}

	public void setUserKey5(String userKey5) {
		this.userKey5 = userKey5;
	}

	public String getUserKey6() {
		return userKey6;
	}

	public void setUserKey6(String userKey6) {
		this.userKey6 = userKey6;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFirmwareFamily() {
		return firmwareFamily;
	}

	public void setFirmwareFamily(String firmwareFamily) {
		this.firmwareFamily = firmwareFamily;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public double getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(double lastModified) {
		this.lastModified = lastModified;
	}

	public double getLastContact() {
		return lastContact;
	}

	public void setLastContact(double lastContact) {
		this.lastContact = lastContact;
	}

	public Boolean getIsContacted() {
		return isContacted;
	}

	public void setIsContacted(Boolean isContacted) {
		this.isContacted = isContacted;
	}	
	
	public long getFirstContactTime() {
		return firstContactTime;
	}

	public void setFirstContactTime(long firstContactTime) {
		this.firstContactTime = firstContactTime;
	}

	public String getFirstContactTimeFormat() {
		
		Date time = new Date(this.firstContactTime);
			
		return JSFUtil.formatarDataHra(time);
		
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
