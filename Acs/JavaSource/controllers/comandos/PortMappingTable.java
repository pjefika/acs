package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.portMapping.PortMappingTableHolder;
import entidades.portMapping.Values;
import models.comandos.PortMappingTableAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class PortMappingTable {
	
	private PortMappingTableHolder portMappingTableHolder;
	
	private Values[] values;
	
	private Integer cont = 0;
	
	private PortMappingTableAction portMappingTableAction;
	
	public PortMappingTable() {

		this.portMappingTableAction = new PortMappingTableAction();
		
	}
	
	public void getPortMappingTableAction(Integer deviceId) {
		
		try {
			
			this.portMappingTableHolder = this.portMappingTableAction.getPortMappingTable(deviceId, JSFUtil.autenticacao());
			
			this.values = this.portMappingTableHolder.getValues();
			
			JSFUtil.addInfoMessage("Busca realizada com sucesso.");
			
			this.cont = 0;			
			
		} catch (Exception e) {
			
			if (cont < 11) {
				
				cont++;
				
				this.getPortMappingTableAction(deviceId);
				
			} else {
				
				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao buscar Port Mapping");
				
				this.cont = 0;
				
			}		
			
		}
		
	}

	public PortMappingTableHolder getPortMappingTableHolder() {
		return portMappingTableHolder;
	}

	public void setPortMappingTableHolder(PortMappingTableHolder portMappingTableHolder) {
		this.portMappingTableHolder = portMappingTableHolder;
	}

	public Values[] getValues() {
		return values;
	}

	public void setValues(Values[] values) {
		this.values = values;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}	

}
