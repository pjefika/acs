package controllers.comandos;

import dal.arris.DeviceDAO;
import dal.arris.RequestCapabilityDiagnosticComplex;
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

    private DeviceDAO dao;

    public GetWiFiConfig() {
        this.wifiConf = new WifiConf();
        wifiConf.setFrequency("2.4GHz");
        this.dao = new DeviceDAO();
    }

    public void consultar() {
        try {
            String oi = dao.request(new RequestCapabilityDiagnosticComplex(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, wifiConf)).getResult();
            String leOi = oi.replace("{\"pivotColumn\":null,\"values\":", "");
//            String[] ola = leOi.split(",");
//            for (String string : ola) {
//                System.out.println(string);
//                      
//            }
            infoHolder = (WifiInfoHolder[]) GsonUtil.convert(leOi.substring(0, leOi.length()-2), WifiInfoHolder[].class);    
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