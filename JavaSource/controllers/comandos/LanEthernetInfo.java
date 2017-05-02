package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.LanEthernetInfo.LanEthernetInfoHolder;
import entidades.LanEthernetInfo.Values;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class LanEthernetInfo extends AcsAbstractBean {

    private LanEthernetInfoHolder lanEthernetInfoHolder;

    private Values[] values;

    public LanEthernetInfo() {
    }

    public void getLanEthernetInfoAction(Integer deviceId) throws IOException {
        String result = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getLanEthernetInfo.name(), deviceId)).getResult();
        System.out.println(result);
        values = (Values[]) GsonUtil.convert(result, Values[].class);
    }

    public LanEthernetInfoHolder getLanEthernetInfoHolder() {
        return lanEthernetInfoHolder;
    }

    public void setLanEthernetInfoHolder(LanEthernetInfoHolder lanEthernetInfoHolder) {
        this.lanEthernetInfoHolder = lanEthernetInfoHolder;
    }

    public Values[] getValues() {
        return values;
    }

    public void setValues(Values[] values) {
        this.values = values;
    }

}
