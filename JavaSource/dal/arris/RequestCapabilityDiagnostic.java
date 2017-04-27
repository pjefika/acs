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
 * @author G0042204
 */
public abstract class RequestCapabilityDiagnostic extends RequestCapability implements ComandoArris {

    public RequestCapabilityDiagnostic(String capability, Integer deviceId) {
        super(capability, deviceId);
    }

    @Override
    public String getRequestUrl() {
        return super.getRequestUrl() + "diagnostic?diagnostic=" + encode(this.capability) + "&deviceId=" + encode(this.deviceId.toString());
    }

    protected String encode(String param) {
        try {
            return URLEncoder.encode("\"" + param + "\"", "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RequestCapabilityDiagnostic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
