package controllers.sys;

import javax.faces.bean.ManagedBean;

import entidades.sys.Autenticacao;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private Autenticacao autenticacao;

	private Autenticacao autenticacaoModifica;

	@EJB
	private AutenticacaoServico autenticacaoServico;

	public AutenticacaoBean() {

		this.autenticacao = new Autenticacao();

		this.autenticacaoModifica = new Autenticacao();		

	}

	public void cadastrarAutenticacao() {

		try {

			this.autenticacaoServico.cadastrarAutenticacao(this.autenticacao);

			JSFUtil.addInfoMessage("Cadastrado com sucesso.");

			this.autenticacao = new Autenticacao();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void modificarAutenticacao() {

		try {

			this.autenticacaoServico.modificarAutenticacao(this.autenticacaoModifica);

			JSFUtil.addInfoMessage("Modificado com sucesso.");

			this.autenticacaoModifica = new Autenticacao();

		} catch (Exception e) { 

			JSFUtil.addErrorMessage(e.getMessage());			

		}

	}

	public void ativaAutenticacao() {

		try {

			List<Autenticacao> listaAutenticacao = this.listarTodasAutenticacao();

			for (Autenticacao autenticacao : listaAutenticacao) {

				if (autenticacao.getId().equals(this.autenticacaoModifica.getId())) {

					this.autenticacaoModifica.setAtivo(true);

					this.autenticacaoServico.modificarAutenticacao(this.autenticacaoModifica);

				} else {

					autenticacao.setAtivo(false);

					this.autenticacaoServico.modificarAutenticacao(autenticacao);

				}

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public List<Autenticacao> listarTodasAutenticacao() {

		return this.autenticacaoServico.listarTodasAutenticacao();

	}

	public Autenticacao listarAutenticacaoAtiva() {
		
		Autenticacao autenticacao = this.autenticacaoServico.listarAutenticacaoAtiva();
		
		return autenticacao;

	}

	public Autenticacao getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}

	public Autenticacao getAutenticacaoModifica() {
		return autenticacaoModifica;
	}

	public void setAutenticacaoModifica(Autenticacao autenticacaoModifica) {
		this.autenticacaoModifica = autenticacaoModifica;
	}

	public AutenticacaoServico getAutenticacaoServico() {
		return autenticacaoServico;
	}

	public void setAutenticacaoServico(AutenticacaoServico autenticacaoServico) {
		this.autenticacaoServico = autenticacaoServico;
	}	

}
