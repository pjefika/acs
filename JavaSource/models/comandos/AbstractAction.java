/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.comandos;

import dal.arris.DeviceDAO;

/**
 *
 * @author G0042204
 */
public abstract class AbstractAction {

    protected DeviceDAO dao = new DeviceDAO();

}
