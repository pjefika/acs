package controllers.comandos;

import entidades.pppoECredentials.PPPoECredentialsHolder;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.comandos.PPPoECredentialsAction;
import models.sys.AutenticacaoServico;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class PPPoECredentials extends AcsAbstractBean {

    private String username;

    private String password;

    private PPPoECredentialsHolder ppPoECredentialsHolder;

    private Integer contador = 0;

    private PPPoECredentialsAction ppPoECredentialsAction;

    @EJB
    private AutenticacaoServico autenticacaoServico;

    public PPPoECredentials() {

        this.ppPoECredentialsAction = new PPPoECredentialsAction();

    }

    public void clearVariables() {

        this.ppPoECredentialsHolder = new PPPoECredentialsHolder();

    }

    public void configPPPoECredentials(Integer deviceId, String parametro) {

        StringBuffer confPppoeBuff = new StringBuffer();

        confPppoeBuff.append("{");

        int cont = 0;

        if (!this.username.isEmpty()) {

            confPppoeBuff.append("\"username\":\"" + this.username + "\"");

            cont++;

        } else if (!this.password.isEmpty()) {

            if (cont != 0) {

                confPppoeBuff.append(",\"password\":\"" + this.password + "\"");

            } else {

                confPppoeBuff.append("\"password\":\"" + this.password + "\"");

            }

        }

        confPppoeBuff.append("}");

        String confPppoe = confPppoeBuff.toString();

        this.setPPPoECredentials(deviceId, confPppoe, parametro);

    }

    public void setPPPoECredentials(Integer deviceId, String confPppoe, String parametro) {
        if (isDeviceOnline(deviceId)) {
            try {

                Thread.sleep(3000);

                this.ppPoECredentialsHolder = this.ppPoECredentialsAction.setPPPoECredentials(deviceId, this.autenticacaoServico.listarAutenticacaoAtiva(), confPppoe);

                if (this.ppPoECredentialsHolder.getStatus().equalsIgnoreCase("ok")) {

                    this.contador = 0;

                    salvarLog(deviceId, ppPoECredentialsHolder, "setPPPoE");

                    JSFUtil.addInfoMessage("Comando executado com sucesso.");

                    this.username = "";
                    this.password = "";

                } else if (this.ppPoECredentialsHolder.getStatus().equalsIgnoreCase("nok") && this.contador < 3) {

                    this.contador++;

                    this.setPPPoECredentials(deviceId, confPppoe, parametro);

                } else {

                    this.contador = 0;

                    JSFUtil.addErrorMessage("Comando: " + this.ppPoECredentialsHolder.getStatus());

                    this.username = "";
                    this.password = "";

                }

            } catch (Exception e) {

                this.contador = 0;

                JSFUtil.addErrorMessage(e.getMessage());

            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PPPoECredentialsAction getPpPoECredentialsAction() {
        return ppPoECredentialsAction;
    }

    public void setPpPoECredentialsAction(PPPoECredentialsAction ppPoECredentialsAction) {
        this.ppPoECredentialsAction = ppPoECredentialsAction;
    }

}
