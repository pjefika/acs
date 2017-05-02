package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.dslConnectionInfo.DslConnectionInfoHolder;
import entidades.dslConnectionInfo.Values;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class DSLConnectionInfo extends AcsAbstractBean {

    private DslConnectionInfoHolder dslConnectionInfoHolder;

    private Values[] valuesDslConnection;

    public DSLConnectionInfo() {
    }

    public void DSLConnectionInfoAction(Integer deviceId) throws IOException {
        String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getDSLConnectionInfo.name(), deviceId)).getResult();
        System.out.println(response);
        valuesDslConnection = (Values[]) GsonUtil.convert(response, Values[].class);
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
