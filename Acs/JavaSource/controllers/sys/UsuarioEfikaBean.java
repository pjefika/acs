package controllers.sys;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.sys.UsuarioEfika;
import models.sys.UsuarioEfikaServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class UsuarioEfikaBean {
	
	private UsuarioEfika usuarioEfika;
	
	@EJB
	private UsuarioEfikaServico usuarioEfikaServico;
	
	public UsuarioEfikaBean() {

		this.usuarioEfika = new UsuarioEfika();
		
	}
	
	public void modificarUsuarioEfika() {
		
		try {
			
			this.usuarioEfikaServico.modificarUsuarioEfika(this.usuarioEfika);
			JSFUtil.addInfoMessage("Usuário modificado com sucesso.");
			this.usuarioEfika = new UsuarioEfika();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public List<UsuarioEfika> listarUsuarioEfikaAdm(Boolean adm) {
		
		return this.usuarioEfikaServico.listarUsuarioEfikaAdm(adm);
		
	}

	public List<UsuarioEfika> listarUsuarioEfikaAtivo(Boolean ativo) {
		
		return this.usuarioEfikaServico.listarUsuarioEfikaAtivo(ativo);
		
	}
	
}
