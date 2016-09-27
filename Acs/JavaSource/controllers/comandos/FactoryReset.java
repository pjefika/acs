package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.factoryReset.FactoryResetHolder;
import models.comandos.FactoryResetAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class FactoryReset {
	
	private FactoryResetHolder factoryResetHolder;
	
	private FactoryResetAction factoryResetAction;
	
	public FactoryReset() {

		this.factoryResetAction = new FactoryResetAction();
		
	}
	
	public void factoryResetAction(Integer deviceId) {

		try {

			this.factoryResetHolder = this.factoryResetAction.factoryReset(deviceId, JSFUtil.autenticacao());

			if (this.factoryResetHolder.getStatus().equalsIgnoreCase("OK")) {

				JSFUtil.addInfoMessage("Reset de Fabrica realizado com sucesso.");

			} else {

				JSFUtil.addInfoMessage("Reset de Fabrica não realizado.");

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao realizar Reset de Fabrica, Equipamento inativo.");

		}

	}

	public FactoryResetHolder getFactoryResetHolder() {
		return factoryResetHolder;
	}

	public void setFactoryResetHolder(FactoryResetHolder factoryResetHolder) {
		this.factoryResetHolder = factoryResetHolder;
	}
	
	

}
