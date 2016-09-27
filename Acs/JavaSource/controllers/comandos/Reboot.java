package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.reboot.RebootHolder;
import models.comandos.RebootAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class Reboot {
	
	private RebootHolder rebootHolder;
	
	private RebootAction rebootAction;
	
	public Reboot() {
		
		this.rebootAction = new RebootAction();
		
	}
	
	public void RebootAction(Integer deviceId) {

		try {

			this.rebootHolder = this.rebootAction.Reboot(deviceId, JSFUtil.autenticacao());

			if (this.rebootHolder.getStatus().equalsIgnoreCase("OK")) {

				JSFUtil.addInfoMessage("Reboot realizado com sucesso, aguarde o modem autenticar.");

			} else {

				JSFUtil.addInfoMessage("Reboot não realizado.");

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao realizar Reboot, Equipamento inativo.");

		}

	}

	public RebootHolder getRebootHolder() {
		return rebootHolder;
	}

	public void setRebootHolder(RebootHolder rebootHolder) {
		this.rebootHolder = rebootHolder;
	}	

}
