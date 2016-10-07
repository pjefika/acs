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
	
	private String retornoRf;
	
	private FactoryResetAction factoryResetAction;
	
	public FactoryReset() {

		this.factoryResetAction = new FactoryResetAction();
		
	}
	
	public void factoryResetAction(Integer deviceId) {

		try {

			this.retornoRf = this.factoryResetAction.factoryReset(deviceId, JSFUtil.autenticacao());

			JSFUtil.addInfoMessage(this.retornoRf);			

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

	public String getRetornoRf() {
		return retornoRf;
	}

	public void setRetornoRf(String retornoRf) {
		this.retornoRf = retornoRf;
	}	

}
