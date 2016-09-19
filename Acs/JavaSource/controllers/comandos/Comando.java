package controllers.comandos;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.validator.constraints.NotEmpty;

import entidades.dados.Results;
import entidades.dados.ResultsHolder;
import entidades.deviceStatus.DeviceStatusHolder;
import entidades.factoryReset.FactoryResetHolder;
import entidades.getInfo.InfoHolder;
import entidades.getInfo.Values;
import entidades.lanHost.LanHostHolder;
import entidades.reboot.RebootHolder;
import entidades.wifiInfo.SetWiFiConfigHolder;
import entidades.wifiInfo.WifiInfoHolder;
import models.comandos.ComandoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class Comando {

	private String criteria;

	@NotEmpty
	private String parameter;

	private Integer id;

	private String type;
	
	private String wifiInput;
	
	private String wifiInputValue;
		

	private ResultsHolder resultsHolder;

	private Results[] listaResults;

	private InfoHolder infoHolder;

	private Values[] values;

	private RebootHolder rebootHolder;

	private LanHostHolder[] lanHostHolders;

	private WifiInfoHolder[] wifiInfoHolders;

	private WifiInfoHolder wifiInfoHolder;
	
	private FactoryResetHolder factoryResetHolder;
	
	private DeviceStatusHolder deviceStatusHolder;

	private SetWiFiConfigHolder setWiFiConfigHolder;


	private ComandoServico comandoServico;

	public Comando() {

		this.comandoServico = new ComandoServico();

	}

	public void listDevices() {

		try {

			if (this.parameter.contains(":")) {

				this.criteria = "mac";

			} else if (this.parameter.contains("-")) {

				this.criteria = "userKey3";

			} else {

				this.criteria = "serialNumber";

			}
			
			this.parameter = this.parameter.trim();

			this.resultsHolder = this.comandoServico.listDevices(this.criteria, this.parameter);

			this.listaResults = this.resultsHolder.getResults();
						
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void getDeviceInfo(Integer deviceId, String classification) {

		try {

			this.id = deviceId;

			this.type = classification.trim();

			this.infoHolder = this.comandoServico.getDeviceInfo(deviceId);

			this.values = this.infoHolder.getValues();

			this.lanHostHolders = null;

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Equipamento inativo.");

		}

	}

	public void Reboot() {

		try {

			this.rebootHolder = this.comandoServico.Reboot(this.id);

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

	public void getLanHostsTable() {

		try {

			this.lanHostHolders = this.comandoServico.getLanHostsTable(this.id);

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao consultar tabela de Hosts, Equipamento inativo.");

		}

	}

	public void getWiFiInfo() {

		try {

			this.wifiInfoHolders = this.comandoServico.getWiFiInfo(this.id);

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

	public void factoryReset() {

		try {

			this.factoryResetHolder = this.comandoServico.factoryReset(this.id);

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
	
	public DeviceStatusHolder DeviceStatus(Integer deviceId) {
		
		try {
			
			this.deviceStatusHolder = this.comandoServico.DeviceStatus(deviceId);
			
			return this.deviceStatusHolder;
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao buscar Status do Device.");
			return null;
			
		}
		
	}
	
	public void setWiFiConfig() {
		
		try {
						
			this.setWiFiConfigHolder = this.comandoServico.setWiFiConfig(this.id, this.wifiInput, this.wifiInputValue);
			
			JSFUtil.addInfoMessage("Comando realizado com sucesso.");		
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao executar comando [Wifi].");
			
		}
		
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public ResultsHolder getResultsHolder() {
		return resultsHolder;
	}

	public void setResultsHolder(ResultsHolder resultsHolder) {
		this.resultsHolder = resultsHolder;
	}

	public Results[] getListaResults() {
		return listaResults;
	}

	public void setListaResults(Results[] listaResults) {
		this.listaResults = listaResults;
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

	public RebootHolder getRebootHolder() {
		return rebootHolder;
	}

	public void setRebootHolder(RebootHolder rebootHolder) {
		this.rebootHolder = rebootHolder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LanHostHolder[] getLanHostHolders() {
		return lanHostHolders;
	}

	public void setLanHostHolders(LanHostHolder[] lanHostHolders) {
		this.lanHostHolders = lanHostHolders;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public FactoryResetHolder getFactoryResetHolder() {
		return factoryResetHolder;
	}

	public void setFactoryResetHolder(FactoryResetHolder factoryResetHolder) {
		this.factoryResetHolder = factoryResetHolder;
	}

	public DeviceStatusHolder getDeviceStatusHolder() {
		return deviceStatusHolder;
	}

	public void setDeviceStatusHolder(DeviceStatusHolder deviceStatusHolder) {
		this.deviceStatusHolder = deviceStatusHolder;
	}

	public SetWiFiConfigHolder getSetWiFiConfigHolder() {
		return setWiFiConfigHolder;
	}

	public void setSetWiFiConfigHolder(SetWiFiConfigHolder setWiFiConfigHolder) {
		this.setWiFiConfigHolder = setWiFiConfigHolder;
	}

	public String getWifiInput() {
		return wifiInput;
	}

	public void setWifiInput(String wifiInput) {
		this.wifiInput = wifiInput;
	}

	public String getWifiInputValue() {
		return wifiInputValue;
	}

	public void setWifiInputValue(String wifiInputValue) {
		this.wifiInputValue = wifiInputValue;
	}	
	
}
