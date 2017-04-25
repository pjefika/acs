package controllers.comandos;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.getInfo.InfoHolder;
import entidades.getInfo.Values;
import models.comandos.DeviceInfoAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class DeviceInfo {

	private InfoHolder infoHolder;

	private Values[] values;

	private Values values2;

	private Boolean ativo = false;

	private Integer id;

	private DeviceInfoAction deviceInfoAction;

	private String status = "Inativo"; 

	private Integer contador = 0;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;

	public DeviceInfo() {

		this.deviceInfoAction = new DeviceInfoAction();

	}


	public void colocarDeviceId(Integer deviceId) {

		this.id = deviceId;

	}

	public void getDeviceInfoAction(Integer deviceId) {

		try {

			this.values = null;

			this.infoHolder = this.deviceInfoAction.getDeviceInfo(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva());

			this.values = this.infoHolder.getValues();

			this.ativo = true;

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Equipamento inativo.");
			this.ativo = false;

		}

	}

	public void getDeviceInfoActionValue(Integer deviceId) {

		try {

			this.values2 = null;

			this.infoHolder = this.deviceInfoAction.getDeviceInfo(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva());

			int cont = 0;

			for (Values values2 : this.infoHolder.getValues()) {

				if (cont == 0) {

					this.values2 = values2;

					cont++;

				}				

			}

			this.ativo = true;

		} catch (Exception e) {

			if (this.contador < 5) {

				this.contador++;

				this.getDeviceInfoActionValue(deviceId);

			} else {

				this.contador = 0;

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Equipamento inativo.");
				this.ativo = false;

			}			

		}

	}

	public String getDeviceInfoActionStatus(Integer deviceId) {

		try {

			this.status = this.deviceInfoAction.getDeviceInfo(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva()).getStatus();

			return this.status;

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

			return this.status;

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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Values getValues2() {
		return values2;
	}

	public void setValues2(Values values2) {
		this.values2 = values2;
	}

}