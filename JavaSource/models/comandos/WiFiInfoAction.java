package models.comandos;

import com.google.gson.Gson;
import dal.arris.ComandoArris;
import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.sys.Autenticacao;
import entidades.wifiInfo.WifiConfIn;
import entidades.wifiInfo.WifiInfoHolder;

public class WiFiInfoAction extends AbstractAction {

    public WifiInfoHolder[] getWiFiInfo(Integer deviceId, Autenticacao autenticacao, String frequency) throws Exception {
        WifiConfIn in = new WifiConfIn();

        Gson gson = new Gson();

        ComandoArris c = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, in));

        return gson.fromJson(c.getResult(), WifiInfoHolder[].class);
    }

}
