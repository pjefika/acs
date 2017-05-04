package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import entidades.lanHost.LanHostHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class LanHostsTable extends AcsAbstractBean {

    private LanHostHolder[] lanHostHolders;

    public LanHostsTable() {
    }

    public void getLanHostsTableAction(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String result = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.getLanHostsTable.name(), deviceId)).getResult();
                lanHostHolders = (LanHostHolder[]) GsonUtil.convert(result, LanHostHolder[].class);
                salvarLog(deviceId, lanHostHolders, EnumCapabilityExecuteSimple.getLanHostsTable.name());
                JSFUtil.addInfoMessage("Tabela de Lan Hosts obtida com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao obter Lan Hosts.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public LanHostHolder[] getLanHostHolders() {
        return lanHostHolders;
    }

    public void setLanHostHolders(LanHostHolder[] lanHostHolders) {
        this.lanHostHolders = lanHostHolders;
    }

}
