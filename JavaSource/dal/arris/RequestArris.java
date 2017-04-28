/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import dal.arris.auth.EnumEndpoint;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author G0042204
 */
public class RequestArris extends Request {

    protected String result;

    public RequestArris() {
        super(EnumEndpoint.ARRIS_PRODUCAO);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    protected String encode(String param) {
        try {
            return URLEncoder.encode("\"" + param + "\"", "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RequestCapabilityDiagnostic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    protected String decode(String param) {
        try {
            return URLEncoder.encode("\"" + param + "\"", "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RequestCapabilityDiagnostic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
