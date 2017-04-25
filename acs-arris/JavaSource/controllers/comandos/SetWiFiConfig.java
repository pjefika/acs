package controllers.comandos;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controllers.sys.LoginBean;
import entidades.sys.Logs;
import entidades.wifiInfo.SetWiFiConfigHolder;
import entidades.wifiInfo.WifiConf;
import models.comandos.SetWiFiConfigAction;
import models.sys.AutenticacaoServico;
import models.sys.LogsServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class SetWiFiConfig {

	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;

	private WifiConf wifiConf;

	private SetWiFiConfigHolder setWiFiConfigHolder;

	private Integer cont = 0;

	private SetWiFiConfigAction setWiFiConfigAction;

	@EJB
	private LogsServico logsServico;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;

	public SetWiFiConfig() {

		this.wifiConf = new WifiConf();

		this.setWiFiConfigAction = new SetWiFiConfigAction();

	}

	public void clearVariables() {

		this.wifiConf = new WifiConf();

	}

	public void configWifiNew(Integer deviceId, String parametro) {

		StringBuffer confWifi = new StringBuffer();

		confWifi.append("{\"frequency\":\""+ this.wifiConf.getFrequency() +"\"");

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

		this.setWiFiConfig(deviceId, passString, parametro);

	}	

	public void setWiFiConfig(Integer deviceId, String confWifi, String parametro) {

		try {

			Thread.sleep(5000);

			this.setWiFiConfigHolder = this.setWiFiConfigAction.setWiFiConfig(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva(), confWifi);

			if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("ok")) {

				this.cont = 0;

				JSFUtil.addInfoMessage("Comando executado com sucesso.");
				
				this.salvarLog(parametro, confWifi);

				this.wifiConf = new WifiConf();

			} else if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("nok") && this.cont < 3) {

				this.cont++;

				this.setWiFiConfig(deviceId, confWifi, parametro);

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

	public void salvarLog(String parametro, String valor) {

		try {

			Logs logs = new Logs();
			Date date = new Date();

			logs.setUsuarioEfika(this.sessao.getUsuario());
			logs.setDataHora(date);
			logs.setComando("SetWifi");
			logs.setParametro(parametro);
			logs.setValor(valor);

			this.logsServico.cadastrarLog(logs);

		} catch (Exception e) {

			System.out.println(e.getMessage());

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

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}	

}
