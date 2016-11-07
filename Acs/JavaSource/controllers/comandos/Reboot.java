package controllers.comandos;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controllers.sys.LoginBean;
import entidades.reboot.RebootHolder;
import entidades.sys.Logs;
import models.comandos.RebootAction;
import models.sys.AutenticacaoServico;
import models.sys.LogsServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class Reboot {

	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;
	
	private RebootHolder rebootHolder;

	private RebootAction rebootAction;

	private Integer count = 0;

	@EJB
	private LogsServico logsServico;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;

	public Reboot() {

		this.rebootAction = new RebootAction();

	}

	public void RebootAction(Integer deviceId, String parametro) {

		try {

			this.rebootHolder = this.rebootAction.Reboot(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva());

			if (this.rebootHolder.getStatus().equalsIgnoreCase("OK")) {

				JSFUtil.addInfoMessage("Reboot realizado com sucesso, aguarde o modem autenticar.");

			} else {

				JSFUtil.addInfoMessage("Reboot não realizado.");

			}

			this.count = 0;
			
			this.salvaLogReboot(parametro, this.rebootHolder.getStatus());

		} catch (Exception e) {

			if (this.count < 11) {

				this.count++;

				this.RebootAction(deviceId, parametro);

			} else {

				this.count = 0;

				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao realizar Reboot, Equipamento inativo.");

			}			

		}

	}

	public void salvaLogReboot(String parametro, String valor) {

		try {
			
			Logs logs = new Logs();
			Date date = new Date();

			logs.setUsuarioEfika(this.sessao.getUsuario());
			logs.setDataHora(date);
			logs.setComando("Reboot");
			logs.setParametro(parametro);
			logs.setValor(valor);
			
			this.logsServico.cadastrarLog(logs);
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		}

	}

	public RebootHolder getRebootHolder() {
		return rebootHolder;
	}

	public void setRebootHolder(RebootHolder rebootHolder) {
		this.rebootHolder = rebootHolder;
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}	

}
