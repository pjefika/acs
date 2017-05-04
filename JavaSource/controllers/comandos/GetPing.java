package controllers.comandos;

import dal.arris.RequestCapabilityDiagnosticComplex;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.ping.PingHolder;
import entidades.ping.PingIn;
import entidades.ping.PingOut;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class GetPing extends AcsAbstractBean {

    private PingHolder[] pingHolder;

    private PingHolder pingHolderShow;

    private PingIn pingIn;

    public GetPing() {
        this.pingIn = new PingIn();
        this.pingHolderShow = new PingHolder();
    }

    public void clearVariables() {
        this.pingHolder = null;
        this.pingHolderShow = null;
    }

    public void PingAction(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityDiagnosticComplex(EnumCapabilityComplex.Ping.name(), deviceId, this.pingIn)).getResult();
                PingOut out = (PingOut) GsonUtil.convert(response, PingOut.class);
                this.pingHolderShow = out.getValues()[0];
                salvarLog(deviceId, pingHolderShow, EnumCapabilityComplex.Ping.name());
                if (this.pingHolderShow != null) {
                    JSFUtil.addInfoMessage("Comando executado com sucesso.");
                } else {
                    JSFUtil.addErrorMessage("Erro ao realizar comando ping.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao realizar comando ping.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public PingHolder getPingHolderShow() {
        return pingHolderShow;
    }

    public void setPingHolderShow(PingHolder pingHolderShow) {
        this.pingHolderShow = pingHolderShow;
    }

    public PingIn getPingIn() {
        return pingIn;
    }

    public void setPingIn(PingIn pingIn) {
        this.pingIn = pingIn;
    }

}
