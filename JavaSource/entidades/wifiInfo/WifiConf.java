package entidades.wifiInfo;

import model.EnumFrequency;

public class WifiConf {

    private String frequency;

    private String ssid;

    private String ssidPassword;

    private String authentication;

    private String encryption;

    private String standard;

    private String channel;

    private String enableStatus;

    private String broadcastEnabled;

    private String radioEnable;

    public WifiConf() {
        this.frequency = EnumFrequency.DEFAULT.getValor();
    }

    public WifiConf(EnumFrequency f) {
        this.frequency = f.getValor();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getSsidPassword() {
        return ssidPassword;
    }

    public void setSsidPassword(String ssidPassword) {
        this.ssidPassword = ssidPassword;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getBroadcastEnabled() {
        return broadcastEnabled;
    }

    public void setBroadcastEnabled(String broadcastEnabled) {
        this.broadcastEnabled = broadcastEnabled;
    }

    public String getRadioEnable() {
        return radioEnable;
    }

    public void setRadioEnable(String radioEnable) {
        this.radioEnable = radioEnable;
    }

}
