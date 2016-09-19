package models.wifiInfo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.wifiInfo.WifiConfig;

@Stateless
public class WifiConfigServico {
	
	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarWifiConfig(WifiConfig wifiConfig) throws Exception {
		
		try {
			
			this.entityManager.persist(wifiConfig);
			
		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Configuração para o Wifi.");
			
		}
		
	}
	
	public void modificarWifiConfig(WifiConfig wifiConfig) throws Exception {
		
		try {
			
			this.entityManager.merge(wifiConfig);
						
		} catch (Exception e) {

			throw new Exception("Erro ao modificar Configuração para o Wifi.");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<WifiConfig> listarWifiConfigAll() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM WifiConfig w");
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<WifiConfig>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<WifiConfig> listarWifiConfigAtivo(Boolean ativo) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM WifiConfig w WHERE w.ativo =:param1");
			query.setParameter("param1", ativo);
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<WifiConfig>();
			
		}
		
	}
	
}
