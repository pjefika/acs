package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.getPPPoECredentials.GetPPPoECredentialsHolder;
import models.comandos.GetPPPoECredentialsAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class GetPPPoECredentials {

	private GetPPPoECredentialsHolder getPPPoECredentialsHolder;

	private Integer contador = 0;

	private GetPPPoECredentialsAction getPPPoECredentialsAction;

	public GetPPPoECredentials() {

		this.getPPPoECredentialsAction = new GetPPPoECredentialsAction();

	}

	public void getPPPoECredentialAction(Integer deviceId) {

		try {

			this.getPPPoECredentialsHolder = this.getPPPoECredentialsAction.getPPPoECredentials(deviceId, JSFUtil.autenticacao());			

			JSFUtil.addInfoMessage("Comando executado com sucesso.");
			
			this.contador = 0;

		} catch (Exception e) {

			if (this.contador < 11) {

				this.contador++;

				this.getPPPoECredentialAction(deviceId);

			} else {

				this.contador = 0;

				JSFUtil.addErrorMessage(e.getMessage());

			}
			
		}

	}

	public GetPPPoECredentialsHolder getGetPPPoECredentialsHolder() {
		return getPPPoECredentialsHolder;
	}

	public void setGetPPPoECredentialsHolder(GetPPPoECredentialsHolder getPPPoECredentialsHolder) {
		this.getPPPoECredentialsHolder = getPPPoECredentialsHolder;
	}

}
