package controllers.comandos;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controllers.sys.LoginBean;
import entidades.factoryReset.FactoryResetHolder;
import entidades.sys.Logs;
import models.comandos.FactoryResetAction;
import models.sys.LogsServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class FactoryReset {
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;
	
	private FactoryResetHolder factoryResetHolder;
	
	private String retornoRf;
	
	private FactoryResetAction factoryResetAction;
	
	@EJB
	private LogsServico logsServico;
	
	public FactoryReset() {

		this.factoryResetAction = new FactoryResetAction();
		
	}
	
	public void factoryResetAction(Integer deviceId, String parametro) {

		try {

			this.retornoRf = this.factoryResetAction.factoryReset(deviceId, JSFUtil.autenticacao());

			this.salvaLogResetFacory(parametro, this.retornoRf);
			
			JSFUtil.addInfoMessage(this.retornoRf);			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao realizar Reset de Fabrica, Equipamento inativo.");

		}

	}
	
	public void salvaLogResetFacory(String parametro, String valor) {

		try {
			
			Logs logs = new Logs();
			Date date = new Date();

			logs.setUsuarioEfika(this.sessao.getUsuario());
			logs.setDataHora(date);
			logs.setComando("Facoty Reset");
			logs.setParametro(parametro);
			logs.setValor(valor);
			
			this.logsServico.cadastrarLog(logs);
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
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

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}	

}
