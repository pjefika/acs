/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.wifiInfo.SetWifiConfResult;
import entidades.wifiInfo.WifiConfIn;
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

    private WifiConfIn wifiConf;
    private SetWifiConfResult setWiFiConfigHolder;
    private WifiInfoHolder[] infoHolder;

    private WifiInfoHolder wifiInfoHolderRedeOne;
    private WifiInfoHolder wifiInfoHolderRedeTwo;

    public WifiConfig() {
        this.wifiConf = new WifiConfIn();
        this.wifiInfoHolderRedeOne = new WifiInfoHolder();
        this.wifiInfoHolderRedeTwo = new WifiInfoHolder();
    }

    public void clearVariables() {
        this.wifiConf = new WifiConfIn();
        this.wifiInfoHolderRedeOne = new WifiInfoHolder();
        this.wifiInfoHolderRedeTwo = new WifiInfoHolder();
    }

    public void buscaInformacoesWifi(Integer deviceId) {
        try {
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, wifiConf)).getResult();
            infoHolder = (WifiInfoHolder[]) GsonUtil.convert(response, WifiInfoHolder[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscaInformacoesWifiConf(Integer deviceId) {
        try {
            this.wifiInfoHolderRedeOne = new WifiInfoHolder();
            this.wifiInfoHolderRedeTwo = new WifiInfoHolder();
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.getLanWiFiInfo.name(), deviceId, wifiConf)).getResult();
            WifiInfoHolder[] holders = (WifiInfoHolder[]) GsonUtil.convert(response, WifiInfoHolder[].class);
            
            for (WifiInfoHolder holder : holders) {
                if(!holder.getSsid().equalsIgnoreCase("GVT Wi-Fi")){
                     this.wifiInfoHolderRedeOne = holder;
                }
                
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWiFiConfig(Integer deviceId, Integer action) {
        if (isDeviceOnline(deviceId)) {
            try {
                WifiInfoHolder hold;
//                if (action == 1) {

                this.wifiInfoHolderRedeOne.setFrequency("2.4GHz");

                hold = this.wifiInfoHolderRedeOne;
                WifiConfIn w = new WifiConfIn(hold);
                System.out.println(GsonUtil.serialize(w));

//                } else {
//                    hold = this.wifiInfoHolderRedeTwo;
//                    this.wifiInfoHolderRedeOne.setFrequency("5GHz");
//                }
                String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.setWiFiConfig.name(), deviceId, w)).getResult();
                System.out.println(response);
                this.setWiFiConfigHolder = (SetWifiConfResult) GsonUtil.convert(response, SetWifiConfResult.class);

                salvarLog(deviceId, setWiFiConfigHolder, EnumCapabilityComplex.getLanWiFiInfo.name());
                if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("ok")) {
                    JSFUtil.addInfoMessage("Comando executado com sucesso.");
                    this.wifiConf = new WifiConfIn();
                } else if (this.setWiFiConfigHolder.getStatus().equalsIgnoreCase("nok")) {
                    if (this.setWiFiConfigHolder.getException().contains("CPE_INVALID_PARAMETER_NAMES")) {
                        JSFUtil.addInfoMessage("Comando executado com sucesso.");
                        this.wifiConf = new WifiConfIn();
                    } else {
                        JSFUtil.addErrorMessage("Erro ao executar commando.");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao executar commando.");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public WifiConfIn getWifiConf() {
        return wifiConf;
    }

    public void setWifiConf(WifiConfIn wifiConf) {
        this.wifiConf = wifiConf;
    }

    public SetWifiConfResult getSetWiFiConfigHolder() {
        return setWiFiConfigHolder;
    }

    public void setSetWiFiConfigHolder(SetWifiConfResult setWiFiConfigHolder) {
        this.setWiFiConfigHolder = setWiFiConfigHolder;
    }

    public WifiInfoHolder[] getInfoHolder() {
        return infoHolder;
    }

    public void setInfoHolder(WifiInfoHolder[] infoHolder) {
        this.infoHolder = infoHolder;
    }

    public WifiInfoHolder getWifiInfoHolderRedeOne() {
        return wifiInfoHolderRedeOne;
    }

    public void setWifiInfoHolderRedeOne(WifiInfoHolder wifiInfoHolderRedeOne) {
        this.wifiInfoHolderRedeOne = wifiInfoHolderRedeOne;
    }

    public WifiInfoHolder getWifiInfoHolderRedeTwo() {
        return wifiInfoHolderRedeTwo;
    }

    public void setWifiInfoHolderRedeTwo(WifiInfoHolder wifiInfoHolderRedeTwo) {
        this.wifiInfoHolderRedeTwo = wifiInfoHolderRedeTwo;
    }

}
