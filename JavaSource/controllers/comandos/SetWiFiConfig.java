package controllers.comandos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.wifiInfo.SetWiFiConfigHolder;
import entidades.wifiInfo.WifiConf;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class SetWiFiConfig extends AcsAbstractBean {

    private WifiConf wifiConf;

    private SetWiFiConfigHolder setWiFiConfigHolder;

    public SetWiFiConfig() {
        this.wifiConf = new WifiConf();
    }

    public void clearVariables() {
        this.wifiConf = new WifiConf();
    }

    public void setWiFiConfig(Integer deviceId) {
        try {
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.setWiFiConfig.name(), deviceId, this.wifiConf)).getResult();
            this.setWiFiConfigHolder = (SetWiFiConfigHolder) GsonUtil.convert(response, SetWiFiConfigHolder.class);
            if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("ok")) {
                JSFUtil.addInfoMessage("Comando executado com sucesso.");
                //Salvar log
                this.wifiConf = new WifiConf();
            } else if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("error")) {
                JSFUtil.addInfoMessage("Erro ao executar commando.");
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getMessage());
        }
    }

    public WifiConf getWifiConf() {
        return wifiConf;
    }

    public void setWifiConf(WifiConf wifiConf) {
        this.wifiConf = wifiConf;
    }

    public SetWiFiConfigHolder getSetWiFiConfigHolder() {
        return setWiFiConfigHolder;
    }

    public void setSetWiFiConfigHolder(SetWiFiConfigHolder setWiFiConfigHolder) {
        this.setWiFiConfigHolder = setWiFiConfigHolder;
    }

}
