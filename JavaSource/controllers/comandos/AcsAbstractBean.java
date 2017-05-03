/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import controllers.sys.LoginBean;
import dal.arris.DeviceDAO;
import dal.arris.RequestCapabilityDiagnosticSimple;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.getInfo.InfoHolder;
import entidades.sys.Logs;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import models.sys.AutenticacaoServico;
import models.sys.LogsServico;
import util.GsonUtil;

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

    protected DeviceDAO dao = new DeviceDAO();

    private InfoHolder infoHolder;

    protected Boolean isDeviceOnline(Integer deviceId) {
        try {
            String response = dao.request(new RequestCapabilityDiagnosticSimple(EnumCapabilitySimple.getDeviceInfo, deviceId)).getResult();
            infoHolder = (InfoHolder) GsonUtil.convert(response, InfoHolder.class);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void salvarLog(String parametro, String valor, String comando) {
        try {
            Logs logs = new Logs();
            Date date = new Date();
            logs.setUsuarioEfika(this.sessao.getUsuario());
            logs.setDataHora(date);
            logs.setComando(comando);
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
}
