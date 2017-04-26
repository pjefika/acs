/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.sys.Autenticacao;

/**
 *
 * @author G0042204
 */
public class AuthFactory {

    public static Autenticacao getEnd() {

        Autenticacao a = new Autenticacao();

        a.setAtivo(Boolean.TRUE);
        a.setLink("http://10.200.6.150/nbbs/api/");
        a.setUser("efika_system");
        a.setPassword("Efika@viv0Gvt");

        return a;
    }

}
