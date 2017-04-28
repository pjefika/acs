/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import controllers.sys.LoginBean;
import entidades.sys.Logs;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import models.sys.AutenticacaoServico;
import models.sys.LogsServico;

/**
 *
 * @author G0042204
 */
public class AcsAbstractBean {

    @ManagedProperty(value = "#{loginBean}")
    protected LoginBean sessao;

    @EJB
    protected LogsServico logsServico;

    @EJB
    protected AutenticacaoServico autenticacaoServico;
    
    protected Integer deviceId;

    public void salvarLog(String parametro, String valor) {

        try {

            Logs logs = new Logs();
            Date date = new Date();

            logs.setUsuarioEfika(this.sessao.getUsuario());
            logs.setDataHora(date);
            logs.setComando("SetWifi");
            logs.setParametro(parametro);
            logs.setValor(valor);

            this.logsServico.cadastrarLog(logs);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
    
    
}
