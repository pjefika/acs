package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.gatewayInfo.GatewayInfoHolder;
import models.comandos.GatewayInfoAction;
import util.JSFUtil;
import entidades.gatewayInfo.Values;

@ManagedBean
@RequestScoped
public class GatewayInfo {
	
	private GatewayInfoHolder gatewayInfoHolder;

	private Values[] valuesgatway;
	
	private Integer cont = 0;
	
	private GatewayInfoAction gatewayInfoAction;
	
	public GatewayInfo() {

		this.gatewayInfoAction = new GatewayInfoAction();
		
	}
	
	public void GatewayInfoAction(Integer deviceId) {

		try {

			this.gatewayInfoHolder = this.gatewayInfoAction.getGatewayInfo(deviceId, JSFUtil.autenticacao());

			this.valuesgatway = this.gatewayInfoHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso,");
			
			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {
				
				this.cont++;
								
				this.GatewayInfoAction(deviceId);				

			} else {

				System.out.println(e.getMessage());

				JSFUtil.addErrorMessage(e.getMessage());
				
				this.cont = 0;

			}	

		}

	}

	public GatewayInfoHolder getGatewayInfoHolder() {
		return gatewayInfoHolder;
	}

	public void setGatewayInfoHolder(GatewayInfoHolder gatewayInfoHolder) {
		this.gatewayInfoHolder = gatewayInfoHolder;
	}

	public Values[] getValuesgatway() {
		return valuesgatway;
	}

	public void setValuesgatway(Values[] valuesgatway) {
		this.valuesgatway = valuesgatway;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}

}
