package models.sys;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sys.Logs;

@Stateless
public class LogsServico {
	
	@PersistenceContext(unitName="vu")
	private EntityManager entityManager;
	
	public void cadastrarLog(Logs logs) throws Exception {
		
		try {
			
			this.entityManager.persist(logs);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Log");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Logs> listarLogsParametro(String parametro) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Logs l WHERE l.parametro =:param1");			
			query.setParameter("param1", parametro);
			return query.getResultList();			
			
		} catch (Exception e) {

			return new ArrayList<Logs>();
			
		}
		
	}
	
}
