/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityExecuteComplex;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import entidades.FirmwareDownload.FirmwareDownHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

/**
 *
 * @author G0034481
 */
@ManagedBean
@RequestScoped
public class FirmwareDownload extends AcsAbstractBean {

    private FirmwareDownHolder firmwareDownHolder;

    public FirmwareDownload() {

    }

    public void firmDown(Integer deviceId) {

        try {
//
            String response = dao.request(new RequestCapabilityExecute(EnumCapabilityExecuteSimple.getPreferredFirmwareVersion.name(), deviceId)).getResult();
            entidades.PreferredFirmwareVersion.PreferredFirmwareVersion preferredFirmwareVersion = (entidades.PreferredFirmwareVersion.PreferredFirmwareVersion) GsonUtil.convert(response, entidades.PreferredFirmwareVersion.PreferredFirmwareVersion.class);
//
            String response1 = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityExecuteComplex.FirmwareDownload.name(), deviceId, preferredFirmwareVersion.getFilename())).getResult();
//            String response1 = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityExecuteComplex.FirmwareDownload.name(), deviceId, "SG790131200114")).getResult();
            this.firmwareDownHolder = (FirmwareDownHolder) GsonUtil.convert(response1, FirmwareDownHolder.class);
//
            System.out.println("dev");

            if (this.firmwareDownHolder.getStatus().equalsIgnoreCase("OK")) {
                JSFUtil.addInfoMessage("Comando realizado com sucesso, aguarde...");
            } else {
                JSFUtil.addErrorMessage("Erro ao realizar comando.");
            }

            // this.salvarLog(parametro, response, response);
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getMessage());
        }

    }

    public FirmwareDownHolder getFirmwareDownHolder() {
        return firmwareDownHolder;
    }

    public void setFirmwareDownHolder(FirmwareDownHolder firmwareDownHolder) {
        this.firmwareDownHolder = firmwareDownHolder;
    }

}
