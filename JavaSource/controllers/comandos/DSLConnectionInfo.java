package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.dslConnectionInfo.DslConnectionInfoHolder;
import entidades.dslConnectionInfo.Values;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class DSLConnectionInfo extends AcsAbstractBean {

    private DslConnectionInfoHolder dslConnectionInfoHolder;

    private Values[] valuesDslConnection;

    public DSLConnectionInfo() {
    }

    public void DSLConnectionInfoAction(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getDSLConnectionInfo.name(), deviceId)).getResult();
                valuesDslConnection = (Values[]) GsonUtil.convert(response, Values[].class);
                this.salvarLog(deviceId, valuesDslConnection, EnumCapabilitySimple.getDSLConnectionInfo.name());
                JSFUtil.addInfoMessage("Parâmetros obtidos com sucesso.");
            } catch (Exception e) {
                JSFUtil.addErrorMessage("Falha ao obter parâmetros.");
                e.printStackTrace();
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }
    }

    public DslConnectionInfoHolder getDslConnectionInfoHolder() {
        return dslConnectionInfoHolder;
    }

    public void setDslConnectionInfoHolder(DslConnectionInfoHolder dslConnectionInfoHolder) {
        this.dslConnectionInfoHolder = dslConnectionInfoHolder;
    }

    public Values[] getValuesDslConnection() {
        return valuesDslConnection;
    }

    public void setValuesDslConnection(Values[] valuesDslConnection) {
        this.valuesDslConnection = valuesDslConnection;
    }

}
