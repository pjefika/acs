package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.pppoECredentials.PPPoECredentialsHolder;
import models.comandos.PPPoECredentialsAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class PPPoECredentials {
	
	private String username;
	
	private String password;
	
	private PPPoECredentialsHolder ppPoECredentialsHolder;
	
	private Integer contador = 0;
	
	private PPPoECredentialsAction ppPoECredentialsAction;
	
	public PPPoECredentials() {

		this.ppPoECredentialsAction = new PPPoECredentialsAction();
		
	}
	
	public void clearVariables() {
		
		this.ppPoECredentialsHolder = new PPPoECredentialsHolder();
		
	}
	
	public void configPPPoECredentials(Integer deviceId) {
		
		StringBuffer confPppoeBuff = new StringBuffer();
		
		confPppoeBuff.append("{");
		
		int cont = 0;
		
		if (!this.username.isEmpty()) {
			
			confPppoeBuff.append("\"username\":\"" + this.username + "\"");
			
			cont++;
			
		} else if (!this.password.isEmpty()) {
			
			if (cont != 0) {
				
				confPppoeBuff.append(",\"password\":\"" + this.password + "\"");	
				
			} else {
				
				confPppoeBuff.append("\"password\":\"" + this.password + "\"");	
				
			}
			
		}
		
		confPppoeBuff.append("}");
		
		String confPppoe = confPppoeBuff.toString();
						
		this.setPPPoECredentials(deviceId, confPppoe);
		
	}
	
	public void setPPPoECredentials(Integer deviceId, String confPppoe) {
		
		try {
			
			Thread.sleep(3000);
			
			this.ppPoECredentialsHolder = this.ppPoECredentialsAction.setPPPoECredentials(deviceId, JSFUtil.autenticacao(), confPppoe);
			
			if (this.ppPoECredentialsHolder.getStatus().equalsIgnoreCase("ok")) {
				
				this.contador = 0;
				
				JSFUtil.addInfoMessage("Comando executado com sucesso.");
				
				this.username = "";
				this.password = "";
				
			} else if (this.ppPoECredentialsHolder.getStatus().equalsIgnoreCase("nok") && this.contador < 3) {
				
				this.contador++;
				
				this.setPPPoECredentials(deviceId, confPppoe);
				
			} else {
				
				this.contador = 0;
				
				JSFUtil.addErrorMessage("Comando: " + this.ppPoECredentialsHolder.getStatus());
				
				this.username = "";
				this.password = "";
				
			}
			
		} catch (Exception e) {
			
			this.contador = 0;

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PPPoECredentialsAction getPpPoECredentialsAction() {
		return ppPoECredentialsAction;
	}

	public void setPpPoECredentialsAction(PPPoECredentialsAction ppPoECredentialsAction) {
		this.ppPoECredentialsAction = ppPoECredentialsAction;
	}	
	
}
