package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.sip.SipDiagnosticsHolder;
import entidades.sip.Values;
import models.comandos.SipDiagnosticsAction;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class SipDiagnostics {
	
	private SipDiagnosticsHolder sipDiagnosticsHolder;
	
	private Values values;
	
	private String phyReferenceList = "1";
	
	private Integer contTentativas = 0;
	
	private SipDiagnosticsAction sipDiagnosticsAction;
	
	public SipDiagnostics() {
		
		this.sipDiagnosticsAction = new SipDiagnosticsAction();
		
	}
	
	public void sipDiagnostics(Integer deviceId) {

		try {
									
			this.sipDiagnosticsHolder = this.sipDiagnosticsAction.sipDiagnostics(deviceId, this.phyReferenceList, JSFUtil.autenticacao());
			
			int cont = 0;

			for (Values value : this.sipDiagnosticsHolder.getValues()) {

				if (cont == 0) {

					this.values = value;

				}

				cont++;

			}
			
			this.contTentativas = 0;

		} catch (Exception e) {
			
			if (contTentativas < 11) {
				
				contTentativas++;
				
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
