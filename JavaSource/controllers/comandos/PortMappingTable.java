package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.portMapping.PortMappingTableHolder;
import entidades.portMapping.Values;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class PortMappingTable extends AcsAbstractBean {

    private PortMappingTableHolder portMappingTableHolder;

    private Values[] values;

    public PortMappingTable() {
    }

    public void getPortMappingTableAction(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String result = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getPortMappingTable.name(), deviceId)).getResult();
                values = (Values[]) GsonUtil.convert(result, Values[].class);
                salvarLog(deviceId, values, EnumCapabilitySimple.getPortMappingTable.name());
                JSFUtil.addInfoMessage("Tabela de Port Mapping obtida com sucesso.");
            } catch (Exception e) {
                JSFUtil.addErrorMessage("Erro ao obter Tabela de Port Mapping.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public PortMappingTableHolder getPortMappingTableHolder() {
        return portMappingTableHolder;
    }

    public void setPortMappingTableHolder(PortMappingTableHolder portMappingTableHolder) {
        this.portMappingTableHolder = portMappingTableHolder;
    }

    public Values[] getValues() {
        return values;
    }

    public void setValues(Values[] values) {
        this.values = values;
    }

}
