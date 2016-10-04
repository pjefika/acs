package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.wifiInfo.SetWiFiConfigHolder;
import entidades.wifiInfo.WifiConf;
import models.comandos.SetWiFiConfigAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class SetWiFiConfig {

	private WifiConf wifiConf;

	private SetWiFiConfigHolder setWiFiConfigHolder;

	private Integer cont = 0;

	private SetWiFiConfigAction setWiFiConfigAction;

	public SetWiFiConfig() {

		this.wifiConf = new WifiConf();

		this.setWiFiConfigAction = new SetWiFiConfigAction();

	}

	public void clearVariables() {

		this.wifiConf = new WifiConf();

	}

	public void configWifi(Integer deviceId) {

		String confWifi;

		String action;

		if (!this.wifiConf.getSsid().isEmpty()) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"ssid\":\"" + this.wifiConf.getSsid() + "\"}";

			action = "SSID";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (!this.wifiConf.getSsidPassword().isEmpty()) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"ssidPassword\":\"" + this.wifiConf.getSsidPassword() + "\"}";

			action = "SSID Password";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getAuthentication() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"authentication\":\"" + this.wifiConf.getAuthentication() + "\"}";

			action = "Authentication";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getEncryption() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"encryption\":\"" + this.wifiConf.getEncryption() + "\"}";

			action = "Encryption";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getStandard() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"standard\":\"" + this.wifiConf.getStandard() + "\"}";

			action = "Standard";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getChannel() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"channel\":\"" + this.wifiConf.getChannel() + "\"}";

			action = "Channel";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getEnableStatus() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"enableStatus\":\"" + this.wifiConf.getEnableStatus() + "\"}";

			action = "EnableStatus";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getBroadcastEnabled() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"broadcastEnabled\":\"" + this.wifiConf.getBroadcastEnabled() + "\"}";

			action = "BroadcastEnabled";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		if (this.wifiConf.getRadioEnable() != null) {

			confWifi = "{\"frequency\":\"2.4GHz\",\"radioEnable\":\"" + this.wifiConf.getRadioEnable() + "\"}";

			action = "RadioEnable";

			this.setWiFiConfig(deviceId, confWifi, action);

		}

		this.wifiConf = new WifiConf();

	}

	public void setWiFiConfig(Integer deviceId, String confWifi, String action) {

		try {

			Thread.sleep(5000);

			this.setWiFiConfigHolder = this.setWiFiConfigAction.setWiFiConfig(deviceId, JSFUtil.autenticacao(), confWifi);

			if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("ok")) {

				JSFUtil.addInfoMessage("Comando " + action + " executado com sucesso.");

				this.cont = 0;

				this.wifiConf = new WifiConf();

			} else if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("nok") && this.cont < 3) {

				this.cont++;

				this.setWiFiConfig(deviceId, confWifi, action);
				
				this.wifiConf = new WifiConf();

			} else {

				JSFUtil.addErrorMessage("Comando: " + this.setWiFiConfigHolder.getStatus() + " - Exception: " + this.setWiFiConfigHolder.getException());

				this.cont = 0;
				
				this.wifiConf = new WifiConf();

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

			this.cont = 0;

		}

	}

	public WifiConf getWifiConf() {
		return wifiConf;
	}

	public void setWifiConf(WifiConf wifiConf) {
		this.wifiConf = wifiConf;
	}

	public SetWiFiConfigHolder getSetWiFiConfigHolder() {
		return setWiFiConfigHolder;
	}

	public void setSetWiFiConfigHolder(SetWiFiConfigHolder setWiFiConfigHolder) {
		this.setWiFiConfigHolder = setWiFiConfigHolder;
	}	

}
