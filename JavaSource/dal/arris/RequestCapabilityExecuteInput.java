/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author G0042204
 */
public class RequestCapabilityExecuteInput extends RequestCapabilityExecute implements ComandoArris {

    protected Object input;

    public RequestCapabilityExecuteInput(String capability, Integer deviceId, Object input) {
        super(capability, deviceId);
        this.input = input;
    }

    @Override
    public String getRequestUrl() {
        return super.getRequestUrl() + "&input=" + encodeAlter(new Gson().toJson(this.input, input.getClass()));
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
