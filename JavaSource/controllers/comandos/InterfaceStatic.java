package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.interfaceStatics.Values;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class InterfaceStatic extends AcsAbstractBean {

    private Values[] values;

    public InterfaceStatic() {
    }

    public void interfaceStatics(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getInterfaceStats.name(), deviceId)).getResult();
                values = (Values[]) GsonUtil.convert(response, Values[].class);
                salvarLog(deviceId, values, EnumCapabilitySimple.getInterfaceStats.name());
                JSFUtil.addInfoMessage("Interface Statistics obtidas com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao obter Interface Statistics.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public Values[] getValues() {
        return values;
    }

}
