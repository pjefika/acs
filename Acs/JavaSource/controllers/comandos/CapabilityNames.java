package controllers.comandos;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import models.comandos.CapabilityNamesAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class CapabilityNames {

	private List<String> capabilitValues;	
	
	private Boolean reboot = false;

	private Boolean factoryReset = false;
	
	private Boolean lanHost = false;
	
	private Boolean interfaceStatistics = false;
	
	private Boolean infoDslConnection = false;
	
	private Boolean gateWayInfo = false;

	private Boolean wifiInfo = false;

	private Boolean fxs = false;

	private Boolean sipDiagnostic = false;	

	private Boolean portMapping = false;
	
	private Boolean lanEthernetInfo = false;	

	private CapabilityNamesAction capabilityNamesAction;

	public CapabilityNames() {
		
		this.capabilityNamesAction = new CapabilityNamesAction();
		
	}

	public void listCapabilityNamesFor(Integer deviceId, Boolean ativo) {

		try {
			
			if (ativo) {

				List<String> values = this.capabilityNamesAction.listCapabilityNamesFor(deviceId, JSFUtil.autenticacao());

				for (String string : values) {

					if (string.equalsIgnoreCase("Reboot")) {

						this.reboot = true;

					} else if (string.equalsIgnoreCase("FactoryReset")) {

						this.factoryReset = true;

					} else if (string.equalsIgnoreCase("getLanHostsTable")) {

						this.lanHost = true;

					} else if (string.equalsIgnoreCase("getLanWiFiInfo")) {

						this.wifiInfo = true;

					} else if (string.equalsIgnoreCase("sipAccountProvisioning")) {

						this.fxs = true;

					} else if (string.equalsIgnoreCase("sipDiagnostics")) {

						this.sipDiagnostic = true;

					} else if (string.equalsIgnoreCase("getInterfaceStats")) {

						this.interfaceStatistics = true;

					} else if (string.equalsIgnoreCase("getDSLConnectionInfo")) {

						this.infoDslConnection = true;

					} else if (string.equalsIgnoreCase("getGatewayInfo")) {

						this.gateWayInfo = true;

					} else if (string.equalsIgnoreCase("getPortMappingTable")) {
						
						this.portMapping = true;
						
					} else if (string.equalsIgnoreCase("getLanEthernetInfo")) {
						
						this.lanEthernetInfo = true;
						
					}

				}

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao buscar Capability Names do Device");

		}		

	}

	public List<String> getCapabilitValues() {
		return capabilitValues;
	}

	public void setCapabilitValues(List<String> capabilitValues) {
		this.capabilitValues = capabilitValues;
	}

	public Boolean getReboot() {
		return reboot;
	}

	public void setReboot(Boolean reboot) {
		this.reboot = reboot;
	}

	public Boolean getFactoryReset() {
		return factoryReset;
	}

	public void setFactoryReset(Boolean factoryReset) {
		this.factoryReset = factoryReset;
	}

	public Boolean getLanHost() {
		return lanHost;
	}

	public void setLanHost(Boolean lanHost) {
		this.lanHost = lanHost;
	}

	public Boolean getWifiInfo() {
		return wifiInfo;
	}

	public void setWifiInfo(Boolean wifiInfo) {
		this.wifiInfo = wifiInfo;
	}

	public Boolean getFxs() {
		return fxs;
	}

	public void setFxs(Boolean fxs) {
		this.fxs = fxs;
	}

	public Boolean getSipDiagnostic() {
		return sipDiagnostic;
	}

	public void setSipDiagnostic(Boolean sipDiagnostic) {
		this.sipDiagnostic = sipDiagnostic;
	}

	public Boolean getInterfaceStatistics() {
		return interfaceStatistics;
	}

	public void setInterfaceStatistics(Boolean interfaceStatistics) {
		this.interfaceStatistics = interfaceStatistics;
	}

	public Boolean getInfoDslConnection() {
		return infoDslConnection;
	}

	public void setInfoDslConnection(Boolean infoDslConnection) {
		this.infoDslConnection = infoDslConnection;
	}

	public Boolean getGateWayInfo() {
		return gateWayInfo;
	}

	public void setGateWayInfo(Boolean gateWayInfo) {
		this.gateWayInfo = gateWayInfo;
	}

	public Boolean getPortMapping() {
		return portMapping;
	}

	public void setPortMapping(Boolean portMapping) {
		this.portMapping = portMapping;
	}

	public Boolean getLanEthernetInfo() {
		return lanEthernetInfo;
	}

	public void setLanEthernetInfo(Boolean lanEthernetInfo) {
		this.lanEthernetInfo = lanEthernetInfo;
	}
	

}
