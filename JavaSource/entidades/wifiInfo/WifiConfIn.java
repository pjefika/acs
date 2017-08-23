package entidades.wifiInfo;

import model.EnumFrequency;

public class WifiConfIn {

    private String index = "1";

    private String frequency;

    private String ssid;

    private String ssidPassword;

    private String authentication;

    private String encryption;

    private String standard;

    private Integer channel;

    private String enableStatus;

    private String broadcastEnabled;

    private String radioEnable;

    public WifiConfIn() {
        this.frequency = EnumFrequency.DEFAULT.getValor();
    }

    public WifiConfIn(WifiInfoHolder h) {
        this.setAuthentication("Shared-Key");
        this.setBroadcastEnabled("true");
        this.setChannel(new Integer(h.getChannel()));
        this.setEnableStatus("true");
        this.setEncryption("AES-TKIP");
        this.setRadioEnable("true");
        this.setSsid(h.getSsid());
        this.setStandard(h.getStandard());
        this.setSsidPassword(h.getSsidPassword());
        this.setFrequency(h.getFrequency());
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public WifiConfIn(EnumFrequency f) {
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

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
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
