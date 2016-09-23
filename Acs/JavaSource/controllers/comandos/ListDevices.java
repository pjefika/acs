package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.validator.constraints.NotEmpty;

import entidades.dados.Results;
import entidades.dados.ResultsHolder;
import models.comandos.ListDevicesServico;
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
	
	public ListDevices() {

		this.listDevicesServico = new ListDevicesServico();
		
	}
	
	public void listDevices() {

		try {

			if (this.parameter.contains(":")) {

				this.criteria = "mac";

			} else if (this.parameter.contains("-")) {

				this.criteria = "userKey3";

			} else {

				this.criteria = "serialNumber";

			}
			
			this.parameter = this.parameter.trim();

			this.resultsHolder = this.listDevicesServico.listDevices(this.criteria, this.parameter);

			this.listaResults = this.resultsHolder.getResults();			
						
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
