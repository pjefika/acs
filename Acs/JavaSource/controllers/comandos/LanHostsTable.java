package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.lanHost.LanHostHolder;
import models.comandos.LanHostsTableAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class LanHostsTable {
	
	private LanHostHolder[] lanHostHolders;
	
	private LanHostsTableAction lanHostsTableAction;
	
	public LanHostsTable() {

		this.lanHostsTableAction = new LanHostsTableAction();
		
	}

	public void getLanHostsTableAction(Integer deviceId) {

		try {

			this.lanHostHolders = this.lanHostsTableAction.getLanHostsTable(deviceId, JSFUtil.autenticacao());

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao consultar tabela de Hosts, Equipamento inativo.");

		}

	}

	public LanHostHolder[] getLanHostHolders() {
		return lanHostHolders;
	}

	public void setLanHostHolders(LanHostHolder[] lanHostHolders) {
		this.lanHostHolders = lanHostHolders;
	}	
	
}
