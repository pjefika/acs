/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

/**
 *
 * @author G0034481
 */
@ManagedBean
@RequestScoped
public class PreferredFirmwareVersion extends AcsAbstractBean {

    private entidades.PreferredFirmwareVersion.PreferredFirmwareVersion preferredFirmwareVersion;

    public PreferredFirmwareVersion() {
    }

    public String buscaFirmware(Integer deviceId) throws Exception {
        System.out.println("Consultou firmware");
        String response = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.getPreferredFirmwareVersion.name(), deviceId)).getResult();
        preferredFirmwareVersion = (entidades.PreferredFirmwareVersion.PreferredFirmwareVersion) GsonUtil.convert(response, entidades.PreferredFirmwareVersion.PreferredFirmwareVersion.class);
        return preferredFirmwareVersion.getFilename();
    }

    public entidades.PreferredFirmwareVersion.PreferredFirmwareVersion getPreferredFirmwareVersion() {
        return preferredFirmwareVersion;
    }

    public void setPreferredFirmwareVersion(entidades.PreferredFirmwareVersion.PreferredFirmwareVersion preferredFirmwareVersion) {
        this.preferredFirmwareVersion = preferredFirmwareVersion;
    }

}
