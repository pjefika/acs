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
public enum EnumCredecialArris {

    PRODUCAO("efika_system", "Efika@viv0Gvt");

    String user, pass;

    private EnumCredecialArris(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

}
