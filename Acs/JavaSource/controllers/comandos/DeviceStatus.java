package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.deviceStatus.DeviceStatusHolder;
import models.comandos.DeviceStatusAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class DeviceStatus {
	
	private DeviceStatusHolder deviceStatusHolder;
	
	private DeviceStatusAction deviceStatusAction;

	public DeviceStatus() {

		this.deviceStatusAction = new DeviceStatusAction();
		
	}

	public void getDeviceStatus(Integer deviceId) {
		
		try {
			
			this.deviceStatusHolder = this.deviceStatusAction.getDeviceStatus(deviceId, JSFUtil.autenticacao());
						
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
