package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.wifiInfo.WifiInfoHolder;
import util.JSFUtil;
import models.comandos.WiFiInfoAction;

@ManagedBean
@RequestScoped
public class WiFiInfo {
	
	private WifiInfoHolder[] wifiInfoHolders;
	
	private WifiInfoHolder wifiInfoHolder;
	
	private WiFiInfoAction wiFiInfoAction;
	
	public WiFiInfo() {

		this.wiFiInfoAction = new WiFiInfoAction();
		
	}
	
	public void WiFiInfoAction(Integer deviceId) {

		try {

			this.wifiInfoHolders = this.wiFiInfoAction.getWiFiInfo(deviceId, JSFUtil.autenticacao());

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

}
