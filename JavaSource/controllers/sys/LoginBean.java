package controllers.sys;

import entidades.sys.UsuarioEfika;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import models.sys.LoginServico;
import util.JSFUtil;
import webservices.Usuario;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable{

	private UsuarioEfika usuario;

	private Usuario usuarioWS;

	private String senha;

	private String pagina;

	@EJB
	private LoginServico servicoLogin;

	private boolean logado;

	public LoginBean() {
		this.usuario = new UsuarioEfika();
		this.logado = false;
	}

	public void validarLogin() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {

			if (!this.logado) {

				context.redirect("/acs-arris/index.jsf");

			}			

		} catch (IOException e) {

			e.printStackTrace();

		}		

	}

	public Boolean validaSeAdm() {

		Boolean adm = false;

		if (this.logado) {
			
			this.usuario = this.servicoLogin.buscaUsuario(this.usuario);

			adm = this.usuario.getAdm();

		}
		
		return adm;

	}
	
	public void redirecionaIndex(Boolean param) {
		
		try {
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			
			if (!param){
											
				context.redirect("/acs-arris/index.jsf");			
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public String logar() {

		try {

			this.usuario.setLogin(this.usuario.getLogin().toUpperCase());

			this.usuarioWS = this.servicoLogin.buscaLoginWS(this.usuario.getLogin());
			this.servicoLogin.autenticaLogin(this.usuarioWS, this.senha);

			this.logado = true;			
			return "Devices/devices.jsf"; 

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			this.usuario = new UsuarioEfika();
			return "";

		}

	}

	public void deslogar() {

		this.usuario = new UsuarioEfika();
		this.logado = false;

	}

	public void validaPagina(String pagina) {

		this.pagina = pagina;

	}	

	public UsuarioEfika getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEfika usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioWS() {
		return usuarioWS;
	}

	public void setUsuarioWS(Usuario usuarioWS) {
		this.usuarioWS = usuarioWS;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
