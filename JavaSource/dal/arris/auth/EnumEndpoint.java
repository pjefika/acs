/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris.auth;

/**
 *
 * @author G0042204
 */
public enum EnumEndpoint {

    ARRIS_PRODUCAO("http://10.200.6.150/nbbs/api/", EnumCredecialArris.PRODUCAO);

    String url;

    EnumCredecialArris cred;

    private EnumEndpoint(String url, EnumCredecialArris cred) {
        this.url = url;
        this.cred = cred;
    }

    public String getUrl() {
        return url;
    }

    public EnumCredecialArris getCred() {
        return cred;
    }

}
