package models.comandos;

import com.google.gson.Gson;
import dal.arris.ComandoArris;
import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.sys.Autenticacao;
import entidades.wifiInfo.WifiConf;
import entidades.wifiInfo.WifiInfoHolder;

public class WiFiInfoAction extends AbstractAction {

    public WifiInfoHolder[] getWiFiInfo(Integer deviceId, Autenticacao autenticacao, String frequency) throws Exception {

        Gson gson = new Gson();
        WifiConf in = new WifiConf();
        in.setFrequency("5GHz");
        gson.toJson(in, WifiConf.class);

        ComandoArris c = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, in));
        gson = new Gson();
        System.out.println(c.getResult());

        return gson.fromJson(c.getResult(), WifiInfoHolder[].class);
    }

}
