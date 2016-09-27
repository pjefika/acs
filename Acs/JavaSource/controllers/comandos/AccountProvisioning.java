package controllers.comandos;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;

import entidades.sip.SipAccountProvisioning;
import entidades.sip.SipAccountProvisioningHolder;
import models.comandos.SipAccountProvisioningAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class AccountProvisioning {
	
	private SipAccountProvisioning sipAccountProvisioning;

	private SipAccountProvisioningHolder sipAccountProvisioningHolder;	
	
	private SipAccountProvisioningAction sipAccountProvisioningAction;
	
	public AccountProvisioning() {
		
		this.sipAccountProvisioning = new SipAccountProvisioning();

		this.sipAccountProvisioningAction = new SipAccountProvisioningAction();
		
	}
	
	public void sipAccountProvisioningAction(Integer deviceId) {

		try {

			this.sipAccountProvisioningHolder = this.sipAccountProvisioningAction.sipAccountProvisioning(deviceId, this.sipAccountProvisioning, JSFUtil.autenticacao());

			if (this.sipAccountProvisioningHolder.getStatusCode().equals(0)) {

				JSFUtil.addInfoMessage("Comando realizado com sucesso.");

				this.sipAccountProvisioning = new SipAccountProvisioning();	

			} else if (this.sipAccountProvisioningHolder.getStatusCode().equals(100)) {

				JSFUtil.addWarnMessage("Por favor preencha todos os campos.");

			} else if (this.sipAccountProvisioningHolder.getStatusCode().equals(400)) {

				JSFUtil.addErrorMessage("Modem inativo por favor verifique.");

				this.sipAccountProvisioning = new SipAccountProvisioning();	

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao enviar comando de FXS.");

		}

	}

	public SipAccountProvisioning getSipAccountProvisioning() {
		return sipAccountProvisioning;
	}

	public void setSipAccountProvisioning(SipAccountProvisioning sipAccountProvisioning) {
		this.sipAccountProvisioning = sipAccountProvisioning;
	}

	public SipAccountProvisioningHolder getSipAccountProvisioningHolder() {
		return sipAccountProvisioningHolder;
	}

	public void setSipAccountProvisioningHolder(SipAccountProvisioningHolder sipAccountProvisioningHolder) {
		this.sipAccountProvisioningHolder = sipAccountProvisioningHolder;
	}	

}
