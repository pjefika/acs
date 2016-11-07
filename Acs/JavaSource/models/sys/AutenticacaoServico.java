package models.sys;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sys.Autenticacao;

@Stateless
public class AutenticacaoServico {

	@PersistenceContext(unitName="vu")
	private EntityManager entityManager;
	
	public void cadastrarAutenticacao(Autenticacao autenticacao) throws Exception {
		
		try {
			
			this.entityManager.persist(autenticacao);
			
		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Autenticação.");
			
		}
		
	}
	
	public void modificarAutenticacao(Autenticacao autenticacao) throws Exception {
		
		try {
			
			this.entityManager.merge(autenticacao);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Autenticacao");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Autenticacao> listarTodasAutenticacao() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Autenticacao a");
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<Autenticacao>();
			
		}
		
	}
	
	public Autenticacao listarAutenticacaoAtiva() {
		
		try {
						
			Query query = this.entityManager.createQuery("FROM Autenticacao a WHERE a.ativo =:param1");
			query.setParameter("param1", true);
			query.setMaxResults(1);
			return (Autenticacao) query.getSingleResult();
			
		} catch (Exception e) {
			
			return new Autenticacao();
			
		}
		
	}
	
}
