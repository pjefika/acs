package controllers.comandos;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controllers.sys.LoginBean;
import entidades.sip.SipAccountProvisioning;
import entidades.sip.SipAccountProvisioningHolder;
import entidades.sys.Logs;
import models.comandos.SipAccountProvisioningAction;
import models.sys.AutenticacaoServico;
import models.sys.LogsServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class AccountProvisioning {
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;

	private SipAccountProvisioning sipAccountProvisioning;

	private SipAccountProvisioningHolder sipAccountProvisioningHolder;	

	private SipAccountProvisioningAction sipAccountProvisioningAction;

	@EJB
	private AutenticacaoServico autenticacaoServico;
	
	@EJB
	private LogsServico logsServico;

	public AccountProvisioning() {

		this.sipAccountProvisioning = new SipAccountProvisioning();

		this.sipAccountProvisioningAction = new SipAccountProvisioningAction();

	}

	public void sipAccountProvisioningAction(Integer deviceId, String parametro) {

		try {

			this.sipAccountProvisioningHolder = this.sipAccountProvisioningAction.sipAccountProvisioning(deviceId, this.sipAccountProvisioning, this.autenticacaoServico.listarAutenticacaoAtiva());

			if (this.sipAccountProvisioningHolder.getStatusCode().equals(0)) {

				JSFUtil.addInfoMessage("Comando realizado com sucesso.");
				
				String valor = "{\"DirectoryNumber\":\"" + this.sipAccountProvisioning.getDirectoryNumber() + "\",\"AuthUserName\":\"" + this.sipAccountProvisioning.getAuthUserName() + "\",\"AuthPassword\":\"" + this.sipAccountProvisioning.getAuthPassword() + "\",\"ProxyServer\":\"" + this.sipAccountProvisioning.getProxyServer() + "\",\"RegistrarServer\":\"" + this.sipAccountProvisioning.getRegistrarServer() + "\",\"UserAgentDomain\":\"" + this.sipAccountProvisioning.getUserAgentDomain() + "\",\"OutboundProxy\":\"" + this.sipAccountProvisioning.getOutboundProxy() + "\",\"PhyReferenceList\":\"" + this.sipAccountProvisioning.getPhyReferenceList() + "\"}";
				
				this.salvaLogSipAccountProvisioning(parametro, valor);

				this.sipAccountProvisioning = new SipAccountProvisioning();	

			} else if (this.sipAccountProvisioningHolder.getStatusCode().equals(100)) {

				JSFUtil.addWarnMessage("Por favor preencha todos os campos.");

			} else if (this.sipAccountProvisioningHolder.getStatusCode().equals(400)) {

				JSFUtil.addErrorMessage("Modem inativo por favor verifique.");

				this.sipAccountProvisioning = new SipAccountProvisioning();	

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			JSFUtil.addErrorMessage("Erro ao enviar comando de FXS.");

		}

	}

	public void salvaLogSipAccountProvisioning(String parametro, String valor) {

		try {

			Logs logs = new Logs();
			Date date = new Date();

			logs.setUsuarioEfika(this.sessao.getUsuario());
			logs.setDataHora(date);
			logs.setComando("AccountProvisioning");
			logs.setParametro(parametro);
			logs.setValor(valor);

			this.logsServico.cadastrarLog(logs);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	public SipAccountProvisioning getSipAccountProvisioning() {
		return sipAccountProvisioning;
	}

	public void setSipAccountProvisioning(SipAccountProvisioning sipAccountProvisioning) {
		this.sipAccountProvisioning = sipAccountProvisioning;
	}

	public SipAccountProvisioningHolder getSipAccountProvisioningHolder() {
		return sipAccountProvisioningHolder;
	}

	public void setSipAccountProvisioningHolder(SipAccountProvisioningHolder sipAccountProvisioningHolder) {
		this.sipAccountProvisioningHolder = sipAccountProvisioningHolder;
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}	

}
