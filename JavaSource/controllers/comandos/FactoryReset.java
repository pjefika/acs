package controllers.comandos;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controllers.sys.LoginBean;
import dal.arris.RequestCoreDevice;
import entidades.factoryReset.FactoryResetHolder;
import models.comandos.FactoryResetAction;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class FactoryReset extends AcsAbstractBean {

    private FactoryResetHolder factoryResetHolder;

    private String retornoRf;

    public FactoryReset() {
    }

    public void factoryResetAction(Integer deviceId, String parametro) {
        try {
            String response = dao.request(new RequestCoreDevice("factoryReset", deviceId)).getResult();            
            this.factoryResetHolder = (FactoryResetHolder) GsonUtil.convert(response, FactoryResetHolder.class);
            
            //Documentação errada nao volta Ok ou Error
            
            switch (this.factoryResetHolder.getStatus()) {
                case "ok":
                    JSFUtil.addInfoMessage("Reset de fábrica realizado com sucesso.");
                    //this.salvarLog(parametro, response, "Facoty Reset");
                    break;
                case "error":
                    JSFUtil.addInfoMessage("Erro ao realizar Reset de fábrica.");
                    break;
                default:
                    break;
            }            
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getMessage());
            JSFUtil.addErrorMessage("Erro ao realizar Reset de Fabrica, Equipamento inativo.");
        }
    }

    public FactoryResetHolder getFactoryResetHolder() {
        return factoryResetHolder;
    }

    public void setFactoryResetHolder(FactoryResetHolder factoryResetHolder) {
        this.factoryResetHolder = factoryResetHolder;
    }

    public String getRetornoRf() {
        return retornoRf;
    }

    public void setRetornoRf(String retornoRf) {
        this.retornoRf = retornoRf;
    }

    public LoginBean getSessao() {
        return sessao;
    }

    public void setSessao(LoginBean sessao) {
        this.sessao = sessao;
    }

}
