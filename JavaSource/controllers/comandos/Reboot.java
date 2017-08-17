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
public class Reboot extends AcsAbstractBean {

    private RebootHolder rebootHolder;

    public Reboot() {
    }

    public void RebootAction(Integer deviceId, String parametro) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.Reboot.name(), deviceId)).getResult();
                rebootHolder = (RebootHolder) GsonUtil.convert(response, RebootHolder.class);
                salvarLog(deviceId, rebootHolder, EnumCapabilityExecuteSimple.Reboot.name());

                System.out.println(GsonUtil.serialize(rebootHolder));
                if (this.rebootHolder.getStatus().equalsIgnoreCase("OK")) {
                    JSFUtil.addInfoMessage("Reboot realizado com sucesso, aguarde o modem autenticar.");
                } else {
                    JSFUtil.addInfoMessage("Reboot não realizado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addInfoMessage("Reboot não realizado.");
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
