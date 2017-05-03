package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import entidades.lanHost.LanHostHolder;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class LanHostsTable extends AcsAbstractBean {

    private LanHostHolder[] lanHostHolders;

    public LanHostsTable() {
    }

    public void getLanHostsTableAction(Integer deviceId) throws IOException {
        String result = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.getLanHostsTable.name(), deviceId)).getResult();
        System.out.println(result);
        lanHostHolders = (LanHostHolder[]) GsonUtil.convert(result, LanHostHolder[].class);
    }

    public LanHostHolder[] getLanHostHolders() {
        return lanHostHolders;
    }

    public void setLanHostHolders(LanHostHolder[] lanHostHolders) {
        this.lanHostHolders = lanHostHolders;
    }

}
