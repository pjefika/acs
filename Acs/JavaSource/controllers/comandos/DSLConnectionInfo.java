package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.dslConnectionInfo.DslConnectionInfoHolder;
import util.JSFUtil;
import entidades.dslConnectionInfo.Values;
import models.comandos.DSLConnectionInfoAction;

@ManagedBean
@RequestScoped
public class DSLConnectionInfo {
	
	private DslConnectionInfoHolder dslConnectionInfoHolder;

	private Values[] valuesDslConnection;
	
	private DSLConnectionInfoAction dslConnectionInfoAction;
	
	private Integer cont = 0;
	
	public DSLConnectionInfo() {

		this.dslConnectionInfoAction = new DSLConnectionInfoAction();
		
	}
	
	public void DSLConnectionInfoAction(Integer deviceId) {

		try {
			
			this.dslConnectionInfoHolder = this.dslConnectionInfoAction.getDSLConnectionInfo(deviceId, JSFUtil.autenticacao());

			this.valuesDslConnection = this.dslConnectionInfoHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");
			
			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {

				this.cont++;

				this.DSLConnectionInfoAction(deviceId);
				
			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				
				this.cont = 0;

			}		

		}

	}

	public DslConnectionInfoHolder getDslConnectionInfoHolder() {
		return dslConnectionInfoHolder;
	}

	public void setDslConnectionInfoHolder(DslConnectionInfoHolder dslConnectionInfoHolder) {
		this.dslConnectionInfoHolder = dslConnectionInfoHolder;
	}

	public Values[] getValuesDslConnection() {
		return valuesDslConnection;
	}

	public void setValuesDslConnection(Values[] valuesDslConnection) {
		this.valuesDslConnection = valuesDslConnection;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}	

}
