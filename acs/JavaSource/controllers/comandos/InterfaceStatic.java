package controllers.comandos;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.interfaceStatics.InterfaceStaticsHolder;
import models.comandos.InterfaceStaticsAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;
import entidades.interfaceStatics.Values;

@ManagedBean
@RequestScoped
public class InterfaceStatic {
	
	private InterfaceStaticsHolder interfaceStaticsHolder;
	
	private Values[] valuesInterfaces;
	
	private InterfaceStaticsAction interfaceStaticsAction;
	
	private Integer cont = 0;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;
	
	public InterfaceStatic() {

		this.interfaceStaticsAction = new InterfaceStaticsAction();
		
	}
	
	public void interfaceStatics(Integer deviceId) {

		try {

			this.interfaceStaticsHolder = this.interfaceStaticsAction.interfaceStatics(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva());

			this.valuesInterfaces = this.interfaceStaticsHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {
				
				this.cont++;
								
				this.interfaceStatics(deviceId);				

			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				
				this.cont = 0;

			}	

		}

	}

	public InterfaceStaticsHolder getInterfaceStaticsHolder() {
		return interfaceStaticsHolder;
	}

	public void setInterfaceStaticsHolder(InterfaceStaticsHolder interfaceStaticsHolder) {
		this.interfaceStaticsHolder = interfaceStaticsHolder;
	}

	public Values[] getValuesInterfaces() {
		return valuesInterfaces;
	}

	public void setValuesInterfaces(Values[] valuesInterfaces) {
		this.valuesInterfaces = valuesInterfaces;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}	

}
