package controllers.comandos;

import dal.arris.RequestCapabilityDiagnosticComplex;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.sip.SipAccountProvisioning;
import entidades.sip.SipDiagnosticsHolder;
import entidades.sip.Values;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class SipDiagnostics extends AcsAbstractBean {

    private SipDiagnosticsHolder sipDiagnosticsHolder;

    private SipAccountProvisioning sipAccountProvisioning;

    private Values values;

    public SipDiagnostics() {
        this.sipAccountProvisioning = new SipAccountProvisioning();
    }

    public void clearVariables() {
        this.values = null;
        this.sipDiagnosticsHolder = null;
    }

    public void sipDiagnostics(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityDiagnosticComplex(EnumCapabilityComplex.sipDiagnostics.name(), deviceId, this.sipAccountProvisioning)).getResult();
                this.sipDiagnosticsHolder = (SipDiagnosticsHolder) GsonUtil.convert(response, SipDiagnosticsHolder.class);
                int cont = 0;
                for (Values value : this.sipDiagnosticsHolder.getValues()) {
                    if (cont == 0) {
                        this.values = value;
                    }
                    cont++;
                }
                salvarLog(deviceId, values, EnumCapabilityComplex.sipDiagnostics.name());
                JSFUtil.addInfoMessage("Busca realizada com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao realizar Sip Diagnostics.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
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

    public SipAccountProvisioning getSipAccountProvisioning() {
        return sipAccountProvisioning;
    }

    public void setSipAccountProvisioning(SipAccountProvisioning sipAccountProvisioning) {
        this.sipAccountProvisioning = sipAccountProvisioning;
    }

}
