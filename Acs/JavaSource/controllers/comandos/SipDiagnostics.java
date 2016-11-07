package controllers.comandos;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.sip.SipDiagnosticsHolder;
import entidades.sip.Values;
import models.comandos.SipDiagnosticsAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class SipDiagnostics {
	
	private SipDiagnosticsHolder sipDiagnosticsHolder;
	
	private Values values;
	
	private String phyReferenceList = "1";
	
	private Integer contTentativas = 0;
	
	private SipDiagnosticsAction sipDiagnosticsAction;
	
	@EJB
	private AutenticacaoServico autenticacaoServico;
	
	public SipDiagnostics() {
		
		this.sipDiagnosticsAction = new SipDiagnosticsAction();
		
	}
	
	public void clearVariables() {
		
		this.values = null;
		
		this.sipDiagnosticsHolder = null;
		
	}
	
	public void sipDiagnostics(Integer deviceId) throws Exception {

		try {
									
			this.sipDiagnosticsHolder = this.sipDiagnosticsAction.sipDiagnostics(deviceId, this.phyReferenceList, this.autenticacaoServico.listarAutenticacaoAtiva());
			
			int cont = 0;

			for (Values value : this.sipDiagnosticsHolder.getValues()) {

				if (cont == 0) {
					
					if (value.getStatus().equalsIgnoreCase("error")) {
						
						cont = 0;
						
						this.sipDiagnostics(deviceId);
						
					} else {
						
						this.values = value;
						
					}					

				}

				cont++;

			}
			
			this.contTentativas = 0;
			
			JSFUtil.addInfoMessage("Busca realizada com sucesso.");

		} catch (Exception e) {
			
			if (this.contTentativas < 11) {
				
				Thread.sleep(1000);
				
				this.contTentativas++;
				
				this.sipDiagnostics(deviceId);
				
			} else {
				
				JSFUtil.addErrorMessage(e.getMessage());
				JSFUtil.addErrorMessage("Erro ao realizar Sip Diagnostics");
				this.contTentativas = 0;
				
			}		

		}

	}

	public SipDiagnosticsHolder getSipDiagnosticsHolder() {
		return sipDiagnosticsHolder;
	}

	public void setSipDiagnosticsHolder(SipDiagnosticsHolder sipDiagnosticsHolder) {
		this.sipDiagnosticsHolder = sipDiagnosticsHolder;
	}

	public Values getValues() {
		return values;
	}

	public void setValues(Values values) {
		this.values = values;
	}

	public String getPhyReferenceList() {
		return phyReferenceList;
	}

	public void setPhyReferenceList(String phyReferenceList) {
		this.phyReferenceList = phyReferenceList;
	}

	public Integer getContTentativas() {
		return contTentativas;
	}

	public void setContTentativas(Integer contTentativas) {
		this.contTentativas = contTentativas;
	}	

}
