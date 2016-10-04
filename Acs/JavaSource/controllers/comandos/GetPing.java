package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.ping.PingHolder;
import models.comandos.PingAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class GetPing {
	
	private PingHolder[] ping;
	
	private PingHolder pingHolder;
	
	private String hostAdress = "www.google.com";
	
	private PingAction pingAction;
	
	private Integer conterTime = 0;
	
	public GetPing() {
		
		this.pingAction = new PingAction();	
		
	}
	
	public void PingAction(Integer deviceId) {
		
		try {
			
			this.ping = this.pingAction.pingAction(deviceId, this.hostAdress, JSFUtil.autenticacao());
			
			int cont = 0;
			
			for (PingHolder pingHolder : ping) {
				
				if (cont == 0) {
					
					this.pingHolder = pingHolder;
					
					cont++;
					
				}				
				
			}
			
			JSFUtil.addInfoMessage("Comando executado com sucesso.");
			
			this.conterTime = 0;
			
		} catch (Exception e) {
			
			if (this.conterTime < 11) {
				
				this.conterTime++;
				
				this.PingAction(deviceId);
				
			} else {
				
				JSFUtil.addErrorMessage(e.getMessage());
				
				this.conterTime = 0;
				
			}
			
			
			
		}
		
	}

	public PingHolder[] getPing() {
		return ping;
	}

	public void setPing(PingHolder[] ping) {
		this.ping = ping;
	}

	public String getHostAdress() {
		return hostAdress;
	}

	public void setHostAdress(String hostAdress) {
		this.hostAdress = hostAdress;
	}

	public PingHolder getPingHolder() {
		return pingHolder;
	}

	public void setPingHolder(PingHolder pingHolder) {
		this.pingHolder = pingHolder;
	}	
	
}
