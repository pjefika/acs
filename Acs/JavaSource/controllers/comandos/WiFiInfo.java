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

	private Integer contTentativas = 0;

	private WiFiInfoAction wiFiInfoAction;

	public WiFiInfo() {

		this.wiFiInfoAction = new WiFiInfoAction();

	}

	public void WiFiInfoAction(Integer deviceId) {

		try {
																		
			this.wifiinfo(deviceId);
						
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}

	}
	
	public void wifiinfo(Integer deviceId) {

		try {			

			this.wifiInfoHolders = this.wiFiInfoAction.getWiFiInfo(deviceId, JSFUtil.autenticacao(), "{\"frequency\":\"2.4GHz\"}");

			this.contTentativas = 0;

		} catch (Exception e) {

			if (this.contTentativas < 11) {

				this.contTentativas++;

				this.wifiinfo(deviceId);

			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao consultar informações do Wifi");
				this.contTentativas = 0;

			}

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

	public Integer getContTentativas() {
		return contTentativas;
	}

	public void setContTentativas(Integer contTentativas) {
		this.contTentativas = contTentativas;
	}
}
