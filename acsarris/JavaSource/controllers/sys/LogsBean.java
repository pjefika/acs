/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sys;

import entidades.sys.Logs;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.sys.LogsServico;

/**
 *
 * @author G0034481
 */
@ManagedBean
@RequestScoped
public class LogsBean {

    private String parametro;

    private List<Logs> listaDeLogs;

    @EJB
    private LogsServico logsServico;

    public LogsBean() {
    }

    public void listarLogsParametro() {

        this.listaDeLogs = this.logsServico.listarLogsParametro(this.getParametro());

    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public List<Logs> getListaDeLogs() {
        return listaDeLogs;
    }

    public void setListaDeLogs(List<Logs> listaDeLogs) {
        this.listaDeLogs = listaDeLogs;
    }

}
