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

	private WifiInfoHolder[] wifiInfoHolders5ghz;

	private WifiInfoHolder wifiInfoHolder;

	private WifiInfoHolder wifiInfoHolder5ghz;

	private Integer contTentativas = 0;

	private WiFiInfoAction wiFiInfoAction;

	public WiFiInfo() {

		this.wiFiInfoAction = new WiFiInfoAction();

	}

	public void WiFiInfoAction(Integer deviceId) {

		try {
									
			this.wifiinfo5ghz(deviceId);
									
			this.wifiinfo24ghz(deviceId);
						
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}

	}
	
	public void wifiinfo24ghz(Integer deviceId) {

		try {			

			this.wifiInfoHolders = this.wiFiInfoAction.getWiFiInfo(deviceId, JSFUtil.autenticacao(), "{\"frequency\":\"2.4GHz\"}");

			int cont = 0;

			for (WifiInfoHolder wifiHolder : wifiInfoHolders) {

				if (cont == 0) {

					this.wifiInfoHolder = wifiHolder;

				}

				cont++;

			}			

			this.contTentativas = 0;

		} catch (Exception e) {

			if (this.contTentativas < 11) {

				this.contTentativas++;

				this.wifiinfo24ghz(deviceId);

			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao consultar informações do Wifi 2.4GHz.");
				this.contTentativas = 0;

			}

		}

	}


	public void wifiinfo5ghz(Integer deviceId) {

		try {
			
			this.wifiInfoHolders5ghz = this.wiFiInfoAction.getWiFiInfo(deviceId, JSFUtil.autenticacao(), "{\"frequency\":\"5GHz\"}");

			int cont = 0;

			for (WifiInfoHolder wifiHolder : wifiInfoHolders5ghz) {

				if (cont == 0) {

					this.wifiInfoHolder5ghz = wifiHolder;

				}

				cont++;

			}			

			this.contTentativas = 0;

		} catch (Exception e) {

			if (this.contTentativas < 11) {

				this.contTentativas++;

				this.wifiinfo5ghz(deviceId);

			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao consultar informações do Wifi 5GHz.");
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

	public WifiInfoHolder[] getWifiInfoHolders5ghz() {
		return wifiInfoHolders5ghz;
	}

	public void setWifiInfoHolders5ghz(WifiInfoHolder[] wifiInfoHolders5ghz) {
		this.wifiInfoHolders5ghz = wifiInfoHolders5ghz;
	}

	public WifiInfoHolder getWifiInfoHolder5ghz() {
		return wifiInfoHolder5ghz;
	}

	public void setWifiInfoHolder5ghz(WifiInfoHolder wifiInfoHolder5ghz) {
		this.wifiInfoHolder5ghz = wifiInfoHolder5ghz;
	}	

}
