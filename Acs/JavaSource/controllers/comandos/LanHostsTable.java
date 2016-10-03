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

	private Integer cont = 0;

	public LanHostsTable() {

		this.lanHostsTableAction = new LanHostsTableAction();

	}

	public void getLanHostsTableAction(Integer deviceId) {

		try {
			
			this.lanHostHolders = this.lanHostsTableAction.getLanHostsTable(deviceId, JSFUtil.autenticacao());

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

			this.cont = 0;

		} catch (Exception e) {

			if (this.cont < 11) {

				this.cont++;

				this.getLanHostsTableAction(deviceId);

			}else {

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao consultar tabela de Hosts, Equipamento inativo.");

				this.cont = 0;

			}

		}

	}

	public LanHostHolder[] getLanHostHolders() {
		return lanHostHolders;
	}

	public void setLanHostHolders(LanHostHolder[] lanHostHolders) {
		this.lanHostHolders = lanHostHolders;
	}	

}
