package controllers.comandos;

import dal.arris.DeviceDAO;
import entidades.wifiInfo.WifiConf;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class GetWiFiConfig extends AcsAbstractBean {

    private WifiConf wifiConf;

    private DeviceDAO dao;

    public GetWiFiConfig() {
        this.wifiConf = new WifiConf();
        this.dao = new DeviceDAO();
    }

    public void consultar() {
        //wifiConf = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, in));
    }

    public WifiConf getWifiConf() {
        return wifiConf;
    }

    public void setWifiConf(WifiConf wifiConf) {
        this.wifiConf = wifiConf;
    }

}
