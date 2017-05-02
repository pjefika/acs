package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.gatewayInfo.GatewayInfoHolder;
import entidades.gatewayInfo.Values;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class GatewayInfo extends AcsAbstractBean {

    private GatewayInfoHolder gatewayInfoHolder;

    private Values[] valuesgatway;

    private Integer cont = 0;

    public GatewayInfo() {
    }

    public void GatewayInfoAction(Integer deviceId) throws IOException {
        String result = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getGatewayInfo.name(), deviceId)).getResult();
        valuesgatway = (Values[]) GsonUtil.convert(result, Values[].class);
    }

    public GatewayInfoHolder getGatewayInfoHolder() {
        return gatewayInfoHolder;
    }

    public void setGatewayInfoHolder(GatewayInfoHolder gatewayInfoHolder) {
        this.gatewayInfoHolder = gatewayInfoHolder;
    }

    public Values[] getValuesgatway() {
        return valuesgatway;
    }

    public void setValuesgatway(Values[] valuesgatway) {
        this.valuesgatway = valuesgatway;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }

}
