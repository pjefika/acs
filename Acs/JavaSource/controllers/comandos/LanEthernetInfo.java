package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.LanEthernetInfo.LanEthernetInfoHolder;
import entidades.LanEthernetInfo.Values;
import models.comandos.LanEthernetInfoAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class LanEthernetInfo {

	private LanEthernetInfoHolder lanEthernetInfoHolder;

	private Values[] values;

	private Integer cont = 0;

	private LanEthernetInfoAction lanEthernetInfoAction;

	public LanEthernetInfo() {

		this.lanEthernetInfoAction = new LanEthernetInfoAction();

	}

	public void getLanEthernetInfoAction(Integer deviceId) {

		try {

			this.lanEthernetInfoHolder = this.lanEthernetInfoAction.getLanEthernetInfo(deviceId, JSFUtil.autenticacao());

			this.values = this.lanEthernetInfoHolder.getValues();

			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

			this.cont = 0;

		} catch (Exception e) {

			if (cont < 11) {

				cont++;

				this.getLanEthernetInfoAction(deviceId);

			} else {

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao buscar Informações de Ethernets");
				this.cont = 0;

			}

		}

	}

	public LanEthernetInfoHolder getLanEthernetInfoHolder() {
		return lanEthernetInfoHolder;
	}

	public void setLanEthernetInfoHolder(LanEthernetInfoHolder lanEthernetInfoHolder) {
		this.lanEthernetInfoHolder = lanEthernetInfoHolder;
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
