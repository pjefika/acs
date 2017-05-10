package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import entidades.reboot.RebootHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class ChangeURLtoMotive extends AcsAbstractBean {

    private RebootHolder rebootHolder;

    public ChangeURLtoMotive() {
    }

    public void action(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.changeURLtoMotive.name(), deviceId)).getResult();
                rebootHolder = (RebootHolder) GsonUtil.convert(response, RebootHolder.class);
                salvarLog(deviceId, rebootHolder, EnumCapabilityExecuteSimple.changeURLtoMotive.name());
                if (this.rebootHolder.getStatus().equalsIgnoreCase("OK")) {
                    salvarLog(deviceId, rebootHolder, EnumCapabilityExecuteSimple.changeURLtoMotive.name());
                    JSFUtil.addInfoMessage("Comando executado com sucesso.");
                } else {
                    JSFUtil.addErrorMessage("Comando não realizado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Comando não realizado.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public RebootHolder getRebootHolder() {
        return rebootHolder;
    }

    public void setRebootHolder(RebootHolder rebootHolder) {
        this.rebootHolder = rebootHolder;
    }

}
