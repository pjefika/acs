/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import dal.arris.auth.EnumEndpoint;

/**
 *
 * @author G0042204
 */
public class RequestArris extends Request {

    public RequestArris() {
        super(EnumEndpoint.ARRIS_PRODUCAO);
    }

}