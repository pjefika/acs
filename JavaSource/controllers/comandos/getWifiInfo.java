package controllers.comandos;

import entidades.wifiInfo.WifiInfoHolder;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.comandos.WiFiInfoAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class getWifiInfo {

	private WifiInfoHolder[] wifiInfoHolders;

	private WifiInfoHolder wifiInfoHolder;

	private Integer contTentativas = 0;

	private WiFiInfoAction wiFiInfoAction;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;

	public getWifiInfo() {

		this.wiFiInfoAction = new WiFiInfoAction();

	}

	public void buscaInformacoesWifi(Integer deviceId) {

		try {

			this.wifiInfoHolders = this.wiFiInfoAction.getWiFiInfo(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva(), "{\"frequency\":\"2.4GHz\"}");

			this.contTentativas = 0;

		} catch (Exception e) {

			if (this.contTentativas < 11) {

				this.contTentativas++;

				this.buscaInformacoesWifi(deviceId);

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
