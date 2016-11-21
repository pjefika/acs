package controllers.comandos;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.validator.constraints.NotEmpty;

import entidades.dados.Results;
import entidades.dados.ResultsHolder;
import models.comandos.ListDevicesServico;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class ListDevices {

	private String criteria;

	@NotEmpty
	private String parameter;

	private ResultsHolder resultsHolder;

	private Results[] listaResults;

	private ListDevicesServico listDevicesServico;

	@EJB
	private AutenticacaoServico autenticacaoServico;

	public ListDevices() {

		this.listDevicesServico = new ListDevicesServico();

	}

	public void listDevices() {

		try {

			this.listaResults = null;

			Thread.sleep(1000);

			if (this.parameter.contains("*")) {

				this.criteria = "";

				if (this.parameter.contains(":")) {

					this.criteria = "mac";

				}

			} else {

				if (this.parameter.contains(":") && this.parameter.length() < 20) {

					this.criteria = "mac";

				} else if (this.parameter.contains("-")) {

					this.criteria = "userKey3";

				} else if (this.parameter.contains(".") || this.parameter.contains(":") && this.parameter.length() > 20) {

					this.criteria = "ip";

				} else {

					this.criteria = "serialNumber";

				}

			}			

			this.parameter = this.parameter.trim();

			this.resultsHolder = this.listDevicesServico.listDevices(this.criteria, this.parameter, this.autenticacaoServico.listarAutenticacaoAtiva());

			this.listaResults = this.resultsHolder.getResults();

			if (this.resultsHolder.getTotalLength() == 0) {

				JSFUtil.addErrorMessage("Não foram encontrados resultados para o Parametro: " + this.parameter);

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public ResultsHolder getResultsHolder() {
		return resultsHolder;
	}

	public void setResultsHolder(ResultsHolder resultsHolder) {
		this.resultsHolder = resultsHolder;
	}

	public Results[] getListaResults() {
		return listaResults;
	}

	public void setListaResults(Results[] listaResults) {
		this.listaResults = listaResults;
	}

}
