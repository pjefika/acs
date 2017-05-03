package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.portMapping.PortMappingTableHolder;
import entidades.portMapping.Values;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class PortMappingTable extends AcsAbstractBean {

    private PortMappingTableHolder portMappingTableHolder;

    private Values[] values;

    public PortMappingTable() {
    }

    public void getPortMappingTableAction(Integer deviceId) throws IOException {
        String result = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getPortMappingTable.name(), deviceId)).getResult();
        System.out.println(result);
        values = (Values[]) GsonUtil.convert(result, Values[].class);
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
