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

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

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

}
