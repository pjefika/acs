package controllers.comandos;

import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.ping.PingHolder;
import entidades.ping.PingIn;
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
        try {
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.Ping.name(), deviceId, this.pingIn)).getResult();
            System.out.println(response);            
            this.pingHolder = (PingHolder[]) GsonUtil.convert(response, PingHolder[].class);
            this.pingHolderShow = this.pingHolder[0];
            if (this.pingHolderShow != null) {
                JSFUtil.addInfoMessage("Comando executado com sucesso.");
            } else {
                JSFUtil.addErrorMessage("Erro ao realizar comando ping");
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getMessage());
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
