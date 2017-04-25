/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import controllers.sys.LoginBean;
import entidades.FirmwareDownload.FirmwareDownHolder;
import entidades.PreferredFirmwareVersion.PreferredFirmwareVersion;
import entidades.sys.Autenticacao;
import entidades.sys.Logs;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import models.comandos.FirmwareDownloadAction;
import models.comandos.GetPreferredFirmwareVersionAction;
import models.sys.AutenticacaoServico;
import models.sys.LogsServico;
import util.JSFUtil;

/**
 *
 * @author G0034481
 */
@ManagedBean
@RequestScoped
public class FirmwareDownload {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean sessao;

    private FirmwareDownHolder firmwareDownHolder;

    private FirmwareDownloadAction firmwareDownloadAction;

    private GetPreferredFirmwareVersionAction getPreferredFirmwareVersionAction;

    private Integer count = 0;

    @EJB
    private LogsServico logsServico;

    @EJB
    private AutenticacaoServico autenticacaoServico;

    public FirmwareDownload() {

        this.firmwareDownloadAction = new FirmwareDownloadAction();
        
        this.getPreferredFirmwareVersionAction = new GetPreferredFirmwareVersionAction();

    }

    public void firmDown(Integer deviceId, String parametro) {

        try {

            Autenticacao autenticacao = this.autenticacaoServico.listarAutenticacaoAtiva();

            PreferredFirmwareVersion preferredFirmwareVersion = this.getPreferredFirmwareVersionAction.getFirmware(deviceId, autenticacao);

            this.firmwareDownHolder = this.firmwareDownloadAction.firmWareDownload(deviceId, autenticacao, preferredFirmwareVersion.getFilename());

            if (this.firmwareDownHolder.getStatus().equalsIgnoreCase("OK")) {

                JSFUtil.addInfoMessage("Comando realizado com sucesso, aguarde...");

            } else {

                JSFUtil.addErrorMessage("Erro ao realizar comando.");

            }

            this.count = 0;

            this.salvaLogReboot(parametro, this.getPreferredFirmwareVersionAction.getFirmware(deviceId, autenticacao).getFilename());

        } catch (Exception e) {

            if (this.count < 11) {

                this.count++;

                this.firmDown(deviceId, parametro);

            } else {

                this.count = 0;

                JSFUtil.addErrorMessage(e.getMessage());
                JSFUtil.addErrorMessage("Erro ao realizar download de Firmware, Equipamento inativo / Firmware nulo.");

            }

        }

    }

    public void salvaLogReboot(String parametro, String valor) {

        try {

            Logs logs = new Logs();
            Date date = new Date();

            logs.setUsuarioEfika(this.sessao.getUsuario());
            logs.setDataHora(date);
            logs.setComando("FirmwareDownload");
            logs.setParametro(parametro);
            logs.setValor(valor);

            this.logsServico.cadastrarLog(logs);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public LoginBean getSessao() {
        return sessao;
    }

    public void setSessao(LoginBean sessao) {
        this.sessao = sessao;
    }

    public FirmwareDownHolder getFirmwareDownHolder() {
        return firmwareDownHolder;
    }

    public void setFirmwareDownHolder(FirmwareDownHolder firmwareDownHolder) {
        this.firmwareDownHolder = firmwareDownHolder;
    }

}
