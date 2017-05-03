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

        try {
            String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getInterfaceStats.name(), deviceId)).getResult();
            System.out.println(response);
            values = (Values[]) GsonUtil.convert(response, Values[].class);

            JSFUtil.addInfoMessage("Busca realizada com sucesso.");
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getMessage());
        }

    }

    public Values[] getValues() {
        return values;
    }

}
