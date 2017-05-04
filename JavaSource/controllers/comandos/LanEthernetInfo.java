package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.LanEthernetInfo.LanEthernetInfoHolder;
import entidades.LanEthernetInfo.Values;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class LanEthernetInfo extends AcsAbstractBean {

    private LanEthernetInfoHolder lanEthernetInfoHolder;

    private Values[] values;

    public LanEthernetInfo() {
    }

    public void getLanEthernetInfoAction(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String result = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getLanEthernetInfo.name(), deviceId)).getResult();
                values = (Values[]) GsonUtil.convert(result, Values[].class);
                salvarLog(deviceId, values, EnumCapabilitySimple.getLanEthernetInfo.name());
                JSFUtil.addInfoMessage("Informações da Lan Ethernet obtidas com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao obter Informações da Lan Ethernet.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

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
