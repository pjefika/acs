package controllers.comandos;

import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.wifiInfo.WifiConf;
import entidades.wifiInfo.WifiInfoHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class GetWiFiConfig extends AcsAbstractBean {

    private WifiConf wifiConf;

    private WifiInfoHolder[] infoHolder;

    public GetWiFiConfig() {
        this.wifiConf = new WifiConf();
        wifiConf.setFrequency("2.4GHz");
    }

    public void consultar() {
        try {
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, wifiConf)).getResult();
            infoHolder = (WifiInfoHolder[]) GsonUtil.convert(response, WifiInfoHolder[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WifiConf getWifiConf() {
        return wifiConf;
    }

    public void setWifiConf(WifiConf wifiConf) {
        this.wifiConf = wifiConf;
    }

    public WifiInfoHolder[] getInfoHolder() {
        return infoHolder;
    }

    public void setInfoHolder(WifiInfoHolder[] infoHolder) {
        this.infoHolder = infoHolder;
    }

}
