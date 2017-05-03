/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author G0041775
 */
public class RequestCoreDeviceList extends RequestArris implements ComandoArris {

    protected String capability;

    protected Integer deviceId;
    
    protected String input;

    public RequestCoreDeviceList(String capability, Integer deviceId, String input) {
        this.capability = capability;
        this.deviceId = deviceId;
        this.input = input;
    }

    @Override
    public String getRequestUrl() {
        return super.getEndPoint().getUrl() + "core/device/listDevices?offset=0&limit=10&criteria=" + encodeAlter(input);
    }

    @Override
    public String getCapability() {
        return capability;
    }

    @Override
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String getResult() {
        return result;
    }
    
    protected String encodeAlter(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RequestCapabilityDiagnostic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
