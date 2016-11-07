package controllers.comandos;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.deviceStatus.DeviceStatusHolder;
import models.comandos.DeviceStatusAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class DeviceStatus {
	
	private DeviceStatusHolder deviceStatusHolder;
	
	private DeviceStatusAction deviceStatusAction;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;

	public DeviceStatus() {

		this.deviceStatusAction = new DeviceStatusAction();
		
	}

	public void getDeviceStatus(Integer deviceId) {
		
		try {
			
			this.deviceStatusHolder = this.deviceStatusAction.getDeviceStatus(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva());
						
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public DeviceStatusHolder getDeviceStatusHolder() {
		return deviceStatusHolder;
	}

	public void setDeviceStatusHolder(DeviceStatusHolder deviceStatusHolder) {
		this.deviceStatusHolder = deviceStatusHolder;
	}	
	
}
