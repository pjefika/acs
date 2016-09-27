package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.dslConnectionInfo.DslConnectionInfoHolder;
import entidades.factoryReset.FactoryResetHolder;
import entidades.gatewayInfo.GatewayInfoHolder;
import entidades.interfaceStatics.InterfaceStaticsHolder;
import entidades.lanHost.LanHostHolder;
import entidades.reboot.RebootHolder;
import entidades.sip.SipAccountProvisioning;
import entidades.sip.SipAccountProvisioningHolder;
import entidades.sip.SipDiagnosticsHolder;
import entidades.sip.Values;
import entidades.wifiInfo.WifiInfoHolder;
import models.comandos.DevicesActionsServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class DevicesActions {	

	private RebootHolder rebootHolder;

	private FactoryResetHolder factoryResetHolder;

	private DevicesActionsServico devicesActionsServico;

	private LanHostHolder[] lanHostHolders;

	private WifiInfoHolder[] wifiInfoHolders;

	private WifiInfoHolder wifiInfoHolder;

	private SipAccountProvisioning sipAccountProvisioning;

	private SipAccountProvisioningHolder sipAccountProvisioningHolder;

	private SipDiagnosticsHolder sipDiagnosticsHolder;

	private Values values;

	private InterfaceStaticsHolder interfaceStaticsHolder;

	private entidades.interfaceStatics.Values[] valuesInterfaces;

	private DslConnectionInfoHolder dslConnectionInfoHolder;

	private entidades.dslConnectionInfo.Values[] valuesDslConnection;

	private GatewayInfoHolder gatewayInfoHolder;

	private entidades.gatewayInfo.Values[] valuesgatway;


	private String phyReferenceList = "1";
	
	private Integer cont = 0;

	public DevicesActions() {

		this.devicesActionsServico = new DevicesActionsServico();

		this.sipAccountProvisioning = new SipAccountProvisioning();

		this.values = new Values();

	}

	public void clearVariables() {

		this.lanHostHolders = null;

	}

	public void Reboot(Integer deviceId) {

		try {

			this.rebootHolder = this.devicesActionsServico.Reboot(deviceId);

			if (this.rebootHolder.getStatus().equalsIgnoreCase("OK")) {

				JSFUtil.addInfoMessage("Reboot realizado com sucesso, aguarde o modem autenticar.");

			} else {

				JSFUtil.addInfoMessage("Reboot não realizado.");

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao realizar Reboot, Equipamento inativo.");

		}

	}

	public void factoryReset(Integer deviceId) {

		try {

			this.factoryResetHolder = this.devicesActionsServico.factoryReset(deviceId);

			if (this.factoryResetHolder.getStatus().equalsIgnoreCase("OK")) {

				JSFUtil.addInfoMessage("Reset de Fabrica realizado com sucesso.");

			} else {

				JSFUtil.addInfoMessage("Reset de Fabrica não realizado.");

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao realizar Reset de Fabrica, Equipamento inativo.");

		}

	}

	public void getLanHostsTable(Integer deviceId) {

		try {

			this.lanHostHolders = this.devicesActionsServico.getLanHostsTable(deviceId);

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao consultar tabela de Hosts, Equipamento inativo.");

		}

	}

	public void getWiFiInfo(Integer deviceId) {

		try {

			this.wifiInfoHolders = this.devicesActionsServico.getWiFiInfo(deviceId);

			int cont = 0;

			for (WifiInfoHolder wifiHolder : wifiInfoHolders) {

				if (cont == 0) {

					this.wifiInfoHolder = wifiHolder;

				}

				cont++;

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao consultar informações do Wifi.");

		}

	}

	public void sipAccountProvisioning(Integer deviceId) {

		try {

			this.sipAccountProvisioningHolder = this.devicesActionsServico.sipAccountProvisioning(deviceId, this.sipAccountProvisioning);

			if (this.sipAccountProvisioningHolder.getStatusCode().equals(0)) {

				JSFUtil.addInfoMessage("Comando realizado com sucesso.");

				this.sipAccountProvisioning = new SipAccountProvisioning();	

			} else if (this.sipAccountProvisioningHolder.getStatusCode().equals(100)) {

				JSFUtil.addWarnMessage("Por favor preencha todos os campos.");

			} else if (this.sipAccountProvisioningHolder.getStatusCode().equals(400)) {

				JSFUtil.addErrorMessage("Modem inativo por favor verifique.");

				this.sipAccountProvisioning = new SipAccountProvisioning();	

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao enviar comando de FXS.");

		}

	}

	public void sipDiagnostics(Integer deviceId) {

		try {

			this.sipDiagnosticsHolder = this.devicesActionsServico.sipDiagnostics(deviceId, this.phyReferenceList);

			int cont = 0;

			for (Values value : this.sipDiagnosticsHolder.getValues()) {

				if (cont == 0) {

					this.values = value;

				}

				cont++;

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void interfaceStatics(Integer deviceId) {

		try {

			this.interfaceStaticsHolder = this.devicesActionsServico.interfaceStatics(deviceId);

			this.valuesInterfaces = this.interfaceStaticsHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {
				
				this.cont++;
								
				this.interfaceStatics(deviceId);				

			} else {

				System.out.println(e.getMessage());

				JSFUtil.addErrorMessage(e.getMessage());
				
				this.cont = 0;

			}	

		}

	}

	public void getDSLConnectionInfo(Integer deviceId) {

		try {

			this.dslConnectionInfoHolder = this.devicesActionsServico.getDSLConnectionInfo(deviceId);

			this.valuesDslConnection = this.dslConnectionInfoHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");
			
			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {

				this.cont++;

				this.getDSLConnectionInfo(deviceId);
				
			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				
				this.cont = 0;

			}		

		}

	}

	public void getGatewayInfoHolder(Integer deviceId) {

		try {

			this.gatewayInfoHolder = this.devicesActionsServico.getGatewayInfoHolder(deviceId);

			this.valuesgatway = this.gatewayInfoHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso,");
			
			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {
				
				this.cont++;
								
				this.getGatewayInfoHolder(deviceId);				

			} else {

				System.out.println(e.getMessage());

				JSFUtil.addErrorMessage(e.getMessage());
				
				this.cont = 0;

			}	

		}

	}

	public RebootHolder getRebootHolder() {
		return rebootHolder;
	}

	public void setRebootHolder(RebootHolder rebootHolder) {
		this.rebootHolder = rebootHolder;
	}

	public FactoryResetHolder getFactoryResetHolder() {
		return factoryResetHolder;
	}

	public void setFactoryResetHolder(FactoryResetHolder factoryResetHolder) {
		this.factoryResetHolder = factoryResetHolder;
	}

	public LanHostHolder[] getLanHostHolders() {
		return lanHostHolders;
	}

	public void setLanHostHolders(LanHostHolder[] lanHostHolders) {
		this.lanHostHolders = lanHostHolders;
	}

	public WifiInfoHolder[] getWifiInfoHolders() {
		return wifiInfoHolders;
	}

	public void setWifiInfoHolders(WifiInfoHolder[] wifiInfoHolders) {
		this.wifiInfoHolders = wifiInfoHolders;
	}

	public WifiInfoHolder getWifiInfoHolder() {
		return wifiInfoHolder;
	}

	public void setWifiInfoHolder(WifiInfoHolder wifiInfoHolder) {
		this.wifiInfoHolder = wifiInfoHolder;
	}

	public SipAccountProvisioning getSipAccountProvisioning() {
		return sipAccountProvisioning;
	}

	public void setSipAccountProvisioning(SipAccountProvisioning sipAccountProvisioning) {
		this.sipAccountProvisioning = sipAccountProvisioning;
	}

	public SipAccountProvisioningHolder getSipAccountProvisioningHolder() {
		return sipAccountProvisioningHolder;
	}

	public void setSipAccountProvisioningHolder(SipAccountProvisioningHolder sipAccountProvisioningHolder) {
		this.sipAccountProvisioningHolder = sipAccountProvisioningHolder;
	}

	public SipDiagnosticsHolder getSipDiagnosticsHolder() {
		return sipDiagnosticsHolder;
	}

	public void setSipDiagnosticsHolder(SipDiagnosticsHolder sipDiagnosticsHolder) {
		this.sipDiagnosticsHolder = sipDiagnosticsHolder;
	}

	public String getPhyReferenceList() {
		return phyReferenceList;
	}

	public void setPhyReferenceList(String phyReferenceList) {
		this.phyReferenceList = phyReferenceList;
	}

	public Values getValues() {
		return values;
	}

	public void setValues(Values values) {
		this.values = values;
	}

	public InterfaceStaticsHolder getInterfaceStaticsHolder() {
		return interfaceStaticsHolder;
	}

	public void setInterfaceStaticsHolder(InterfaceStaticsHolder interfaceStaticsHolder) {
		this.interfaceStaticsHolder = interfaceStaticsHolder;
	}

	public entidades.interfaceStatics.Values[] getValuesInterfaces() {
		return valuesInterfaces;
	}

	public void setValuesInterfaces(entidades.interfaceStatics.Values[] valuesInterfaces) {
		this.valuesInterfaces = valuesInterfaces;
	}

	public DslConnectionInfoHolder getDslConnectionInfoHolder() {
		return dslConnectionInfoHolder;
	}

	public void setDslConnectionInfoHolder(DslConnectionInfoHolder dslConnectionInfoHolder) {
		this.dslConnectionInfoHolder = dslConnectionInfoHolder;
	}

	public entidades.dslConnectionInfo.Values[] getValuesDslConnection() {
		return valuesDslConnection;
	}

	public void setValuesDslConnection(entidades.dslConnectionInfo.Values[] valuesDslConnection) {
		this.valuesDslConnection = valuesDslConnection;
	}

	public GatewayInfoHolder getGatewayInfoHolder() {
		return gatewayInfoHolder;
	}

	public void setGatewayInfoHolder(GatewayInfoHolder gatewayInfoHolder) {
		this.gatewayInfoHolder = gatewayInfoHolder;
	}

	public entidades.gatewayInfo.Values[] getValuesgatway() {
		return valuesgatway;
	}

	public void setValuesgatway(entidades.gatewayInfo.Values[] valuesgatway) {
		this.valuesgatway = valuesgatway;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}	

}
