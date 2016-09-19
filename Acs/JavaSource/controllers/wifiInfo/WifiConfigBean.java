package controllers.wifiInfo;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.wifiInfo.WifiConfig;
import models.wifiInfo.WifiConfigServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class WifiConfigBean {
	
	private WifiConfig wifiConfig;
	
	private WifiConfig wifiConfigModifica;
	
	@EJB
	private WifiConfigServico wifiConfigServico;
	
	public WifiConfigBean() {

		this.wifiConfig = new WifiConfig();
		
		this.wifiConfigModifica = new WifiConfig();
		
	}
	
	public void cadastrarWifiConfig() {
		
		try {
			
			this.wifiConfigServico.cadastrarWifiConfig(this.wifiConfig);
			JSFUtil.addInfoMessage("Configuração cadastrada com sucesso.");
			this.wifiConfig = new WifiConfig();
						
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void modificarWifiConfig() {
		
		try {
			
			this.wifiConfigServico.modificarWifiConfig(this.wifiConfigModifica);
			JSFUtil.addInfoMessage("Configuração modificada com sucesso.");
			this.wifiConfigModifica = new WifiConfig();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());			
			
		}
		
	}
	
	public List<WifiConfig> listarWifiConfigAll() {
		
		return this.wifiConfigServico.listarWifiConfigAll();
		
	}
	
	public List<WifiConfig> listarWifiConfigAtivo(Boolean ativo) {
		
		return this.wifiConfigServico.listarWifiConfigAtivo(ativo);
		
	}

	public WifiConfig getWifiConfig() {
		return wifiConfig;
	}

	public void setWifiConfig(WifiConfig wifiConfig) {
		this.wifiConfig = wifiConfig;
	}

	public WifiConfig getWifiConfigModifica() {
		return wifiConfigModifica;
	}

	public void setWifiConfigModifica(WifiConfig wifiConfigModifica) {
		this.wifiConfigModifica = wifiConfigModifica;
	}	
	
}
