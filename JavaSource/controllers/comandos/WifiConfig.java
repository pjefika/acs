/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.wifiInfo.SetWiFiConfigHolder;
import entidades.wifiInfo.WifiConf;
import entidades.wifiInfo.WifiInfoHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

/**
 *
 * @author G0034481
 */
@ManagedBean
@RequestScoped
public class WifiConfig extends AcsAbstractBean {

    private WifiConf wifiConf;
    private SetWiFiConfigHolder setWiFiConfigHolder;
    private WifiInfoHolder[] infoHolder;

    public WifiConfig() {
        this.wifiConf = new WifiConf();
        this.wifiConf.setFrequency("2.4GHz");
    }

    public void clearVariables() {
        this.wifiConf = new WifiConf();
    }

    public void buscaInformacoesWifi(Integer deviceId) {
        try {
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, wifiConf)).getResult();
            infoHolder = (WifiInfoHolder[]) GsonUtil.convert(response, WifiInfoHolder[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public WifiInfoHolder[] getInfoHolder() {
        return infoHolder;
    }

    public void setInfoHolder(WifiInfoHolder[] infoHolder) {
        this.infoHolder = infoHolder;
    }

}
