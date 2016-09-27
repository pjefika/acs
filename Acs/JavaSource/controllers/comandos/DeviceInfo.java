package controllers.comandos;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.getInfo.InfoHolder;
import entidades.getInfo.Values;
import models.comandos.DeviceInfoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class DeviceInfo {

	private InfoHolder infoHolder;

	private Values[] values;

	private List<String> capabilitValues;

	private String type;

	private Integer id;	

	private Boolean reboot = false;

	private Boolean factoryReset = false;

	private Boolean lanHost = false;

	private Boolean wifiInfo = false;

	private Boolean fxs = false;

	private Boolean sipDiagnostic = false;

	private Boolean interfaceStatistics = false;

	private Boolean infoDslConnection = false;

	private Boolean gateWayInfo = false;


	private DeviceInfoServico deviceInfoServico;

	public DeviceInfo() {

		this.deviceInfoServico = new DeviceInfoServico();

	}

	public void acao(Integer deviceId, String classification) {

		this.values = null;		
		this.id = deviceId;
		this.type = classification.trim();

		this.getDeviceInfo();	

		this.listCapabilityNamesFor();

	}

	public void getDeviceInfo() {

		try {

			this.infoHolder = this.deviceInfoServico.getDeviceInfo(this.id);

			this.values = this.infoHolder.getValues();			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Equipamento inativo.");

		}

	}

	public void listCapabilityNamesFor() {

		try {

			this.reboot = false;
			this.factoryReset = false;
			this.lanHost = false;
			this.wifiInfo = false;
			this.fxs = false;
			this.sipDiagnostic = false;
			this.interfaceStatistics = false;
			this.infoDslConnection = false;
			this.gateWayInfo = false;


			if (this.getValues()!=null) {

				List<String> values = this.deviceInfoServico.listCapabilityNamesFor(this.id);

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

					}

				}

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao buscar Capability Names do Device");

		}		

	}


	public InfoHolder getInfoHolder() {
		return infoHolder;
	}

	public void setInfoHolder(InfoHolder infoHolder) {
		this.infoHolder = infoHolder;
	}

	public Values[] getValues() {
		return values;
	}

	public void setValues(Values[] values) {
		this.values = values;
	}

	public List<String> getCapabilitValues() {
		return capabilitValues;
	}

	public void setCapabilitValues(List<String> capabilitValues) {
		this.capabilitValues = capabilitValues;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
