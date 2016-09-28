package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.getInfo.InfoHolder;
import entidades.getInfo.Values;
import models.comandos.DeviceInfoAction;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class DeviceInfo {

	private InfoHolder infoHolder;

	private Values[] values;
	
	private Boolean ativo = false;
	
	private Integer id;

	private DeviceInfoAction deviceInfoAction;

	public DeviceInfo() {

		this.deviceInfoAction = new DeviceInfoAction();

	}

	public void getDeviceInfoAction(Integer deviceId) {

		try {
			
			this.values = null;
			
			this.id = deviceId;

			this.infoHolder = this.deviceInfoAction.getDeviceInfo(deviceId, JSFUtil.autenticacao());

			this.values = this.infoHolder.getValues();
			
			this.ativo = true;			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Equipamento inativo.");
			this.ativo = false;

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

}
