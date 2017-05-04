package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import entidades.reboot.RebootHolder;
import java.io.IOException;
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

    public void action(Integer deviceId, String parametro) {

        try {

            String response = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.changeURLtoMotive.name(), deviceId)).getResult();
            System.out.println(response);
            rebootHolder = (RebootHolder) GsonUtil.convert(response, RebootHolder.class);

            if (this.rebootHolder.getStatus().equalsIgnoreCase("OK")) {
                JSFUtil.addInfoMessage("Reboot realizado com sucesso, aguarde o modem autenticar.");
            } else {
                JSFUtil.addInfoMessage("Reboot não realizado.");
            }
        } catch (IOException e) {
            JSFUtil.addErrorMessage(e.getMessage());
        }
    }

    public RebootHolder getRebootHolder() {
        return rebootHolder;
    }

    public void setRebootHolder(RebootHolder rebootHolder) {
        this.rebootHolder = rebootHolder;
    }

}
