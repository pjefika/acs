/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.comandos.GetPreferredFirmwareVersionAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

/**
 *
 * @author G0034481
 */
@ManagedBean
@RequestScoped
public class PreferredFirmwareVersion {
    
    private entidades.PreferredFirmwareVersion.PreferredFirmwareVersion preferredFirmwareVersion;
    
    private GetPreferredFirmwareVersionAction getPreferredFirmwareVersionAction;
    
    private Integer count = 0;

    @EJB
    private AutenticacaoServico autenticacaoServico;
    
    public PreferredFirmwareVersion() {
        
        this.preferredFirmwareVersion = new entidades.PreferredFirmwareVersion.PreferredFirmwareVersion();
        
        this.getPreferredFirmwareVersionAction = new GetPreferredFirmwareVersionAction();       
        
    }
    
    public void buscaFirmware(Integer deviceId) {
        
        try {
            
            this.preferredFirmwareVersion = this.getPreferredFirmwareVersionAction.getFirmware(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva());
                        
            this.count = 0;
            
        } catch (Exception e) {
            
            if (this.count < 11) {
                
                this.count++;
                
                this.buscaFirmware(deviceId);
                
            } else {
                
                this.count = 0;

                JSFUtil.addErrorMessage(e.getMessage());
                JSFUtil.addErrorMessage("Erro ao buscar Firmware Preferencial, Equipamento inativo.");
                
            }
            
        }
        
    }

    public entidades.PreferredFirmwareVersion.PreferredFirmwareVersion getPreferredFirmwareVersion() {
        return preferredFirmwareVersion;
    }

    public void setPreferredFirmwareVersion(entidades.PreferredFirmwareVersion.PreferredFirmwareVersion preferredFirmwareVersion) {
        this.preferredFirmwareVersion = preferredFirmwareVersion;
    } 
    
    
}
