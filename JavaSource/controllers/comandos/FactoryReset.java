package controllers.comandos;

import controllers.sys.LoginBean;
import dal.arris.RequestCoreDevice;
import entidades.factoryReset.FactoryResetHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class FactoryReset extends AcsAbstractBean {

    private FactoryResetHolder factoryResetHolder;

    private String retornoRf;

    public FactoryReset() {
    }

    public void factoryResetAction(Integer deviceId, String parametro) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCoreDevice("factoryReset", deviceId)).getResult();
//            this.factoryResetHolder = (FactoryResetHolder) GsonUtil.convert(response, FactoryResetHolder.class);
                //Documentação errada nao volta Ok ou Error

                if (new Integer(response).compareTo(0) > 0) {
                    JSFUtil.addInfoMessage("Reset de fábrica realizado com sucesso.");
                    salvarLog(deviceId, Boolean.TRUE, "factoryReset");
                } else {
                    JSFUtil.addInfoMessage("Erro ao realizar Reset de fábrica.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao realizar Reset de Fabrica.");
            }
        }else{
            JSFUtil.addErrorMessage("Modem inativo.");
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
