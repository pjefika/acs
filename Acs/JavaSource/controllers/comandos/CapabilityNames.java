package controllers.comandos;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import models.comandos.CapabilityNamesAction;
import util.JSFUtil;

@ManagedBean
@ViewScoped
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
	
	private Boolean ping = false;
	
	private Boolean setWiFiConfig = false;
	

	private CapabilityNamesAction capabilityNamesAction;

	public CapabilityNames() {
		
		this.capabilityNamesAction = new CapabilityNamesAction();
		
	}

	public void listCapabilityNamesFor(Integer deviceId, Boolean ativo) {

		try {
			
			this.everyBodyFalse();
			
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
						
					} else if (string.equalsIgnoreCase("Ping")) {
						
						this.ping = true;
						
					} else if (string.equalsIgnoreCase("setWiFiConfig")) {
						
						this.setWiFiConfig = true;
						
					}

				}
				
			}
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao buscar Capability Names do Device");

		}		

	}
	
	public void everyBodyFalse() {
		
		/**
		 * Every body wants a kung fu fighter =)
		 * False em todo mundo
		 **/
		
		this.reboot = false;

		this.factoryReset = false;
		
		this.lanHost = false;
		
		this.interfaceStatistics = false;
		
		this.infoDslConnection = false;
		
		this.gateWayInfo = false;

		this.wifiInfo = false;

		this.fxs = false;

		this.sipDiagnostic = false;	

		this.portMapping = false;
		
		this.lanEthernetInfo = false;
		
		this.ping = false;
		
		this.setWiFiConfig = false;
		
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

	public Boolean getPing() {
		return ping;
	}

	public void setPing(Boolean ping) {
		this.ping = ping;
	}

	public Boolean getSetWiFiConfig() {
		return setWiFiConfig;
	}

	public void setSetWiFiConfig(Boolean setWiFiConfig) {
		this.setWiFiConfig = setWiFiConfig;
	}
	
}
