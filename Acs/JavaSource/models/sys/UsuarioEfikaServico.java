package models.sys;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sys.UsuarioEfika;

@Stateless
public class UsuarioEfikaServico {
	
	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void modificarUsuarioEfika(UsuarioEfika usuarioEfika) throws Exception {
		
		try {
			
			this.entityManager.merge(usuarioEfika);
						
		} catch (Exception e) {

			throw new Exception("Erro ao modificar Usuário");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEfika> listarUsuarioEfikaAdm(Boolean adm) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM UsuarioEfika u WHERE u.adm =:param1");
			query.setParameter("param1", adm);
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<UsuarioEfika>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEfika> listarUsuarioEfikaAtivo(Boolean ativo) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM UsuarioEfika u WHERE u.ativo =:param1");
			query.setParameter("param1", ativo);
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<UsuarioEfika>();
			
		}
		
	}

}
