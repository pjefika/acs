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
	
	public void configWifiNew(Integer deviceId) {
		
		StringBuffer confWifi = new StringBuffer();
		
		confWifi.append("{\"frequency\":\"2.4GHz\"");
		
		/*
		 * Regra no meio.
		 * */
		
		if (!this.wifiConf.getSsid().isEmpty()) {
			
			confWifi.append(",\"ssid\":\"" + this.wifiConf.getSsid() + "\"");
			
		}
		
		if (!this.wifiConf.getSsidPassword().isEmpty()) {

			confWifi.append(",\"ssidPassword\":\"" + this.wifiConf.getSsidPassword() + "\"");


		}

		if (this.wifiConf.getAuthentication() != null) {

			confWifi.append(",\"authentication\":\"" + this.wifiConf.getAuthentication() + "\"");


		}

		if (this.wifiConf.getEncryption() != null) {

			confWifi.append(",\"encryption\":\"" + this.wifiConf.getEncryption() + "\"");


		}

		if (this.wifiConf.getStandard() != null) {

			confWifi.append(",\"standard\":\"" + this.wifiConf.getStandard() + "\"");


		}

		if (this.wifiConf.getChannel() != null) {

			confWifi.append(",\"channel\":" + this.wifiConf.getChannel() + "");


		}

		if (this.wifiConf.getEnableStatus() != null) {

			confWifi.append(",\"enableStatus\":\"" + this.wifiConf.getEnableStatus() + "\"");


		}

		if (this.wifiConf.getBroadcastEnabled() != null) {

			confWifi.append(",\"broadcastEnabled\":\"" + this.wifiConf.getBroadcastEnabled() + "\"");


		}

		if (this.wifiConf.getRadioEnable() != null) {

			confWifi.append(",\"radioEnable\":\"" + this.wifiConf.getRadioEnable() + "\"");


		}		
		
		confWifi.append("}");
		
		String passString = confWifi.toString();
				
		this.setWiFiConfig(deviceId, passString);
				
	}	

	public void setWiFiConfig(Integer deviceId, String confWifi) {

		try {

			Thread.sleep(5000);
			
			this.setWiFiConfigHolder = this.setWiFiConfigAction.setWiFiConfig(deviceId, JSFUtil.autenticacao(), confWifi);

			if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("ok")) {
				
				this.cont = 0;

				JSFUtil.addInfoMessage("Comando executado com sucesso.");

				this.wifiConf = new WifiConf();

			} else if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("nok") && this.cont < 3) {

				this.cont++;

				this.setWiFiConfig(deviceId, confWifi);
				
			} else {
				
				this.cont = 0;

				JSFUtil.addErrorMessage("Comando: " + this.setWiFiConfigHolder.getStatus() + " - Exception: " + this.setWiFiConfigHolder.getException());
				
				this.wifiConf = new WifiConf();

			}

		} catch (Exception e) {
			
			this.cont = 0;

			JSFUtil.addErrorMessage(e.getMessage());

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
