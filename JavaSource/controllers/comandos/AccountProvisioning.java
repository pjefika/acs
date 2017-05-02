package controllers.comandos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dal.arris.RequestCapabilityExecuteInput;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.sip.SipAccountProvisioning;
import entidades.sip.SipAccountProvisioningHolder;
import entidades.sys.Logs;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class AccountProvisioning extends AcsAbstractBean {

    private SipAccountProvisioning sipAccountProvisioning;

    private SipAccountProvisioningHolder sipAccountProvisioningHolder;

    public AccountProvisioning() {
        this.sipAccountProvisioning = new SipAccountProvisioning();
    }

    public void sipAccountProvisioningAction(Integer deviceId, String parametro) {
        try {
            String response = dao.request(new RequestCapabilityExecuteInput(EnumCapabilityComplex.sipAccountProvisioning.name(), deviceId, sipAccountProvisioning)).getResult();
            this.sipAccountProvisioningHolder = (SipAccountProvisioningHolder) GsonUtil.convert(response, SipAccountProvisioningHolder.class);
            switch (this.sipAccountProvisioningHolder.getStatusCode()) {
                case 0:
                    this.sipAccountProvisioning = new SipAccountProvisioning();
                    //this.salvarLog(parametro, response, EnumCapabilityComplex.sipAccountProvisioning.name());
                    JSFUtil.addInfoMessage("Comando realizado com sucesso.");
                    break;
                case 100:
                    JSFUtil.addWarnMessage("Por favor preencha todos os campos.");
                    break;
                case 400:
                    this.sipAccountProvisioning = new SipAccountProvisioning();
                    JSFUtil.addErrorMessage("Modem inativo por favor verifique.");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("Erro ao enviar comando de FXS.");
            e.printStackTrace();
        }
    }

    public void salvaLogSipAccountProvisioning(String parametro, String valor) {

        try {

            Logs logs = new Logs();
            Date date = new Date();

            logs.setUsuarioEfika(this.sessao.getUsuario());
            logs.setDataHora(date);
            logs.setComando("AccountProvisioning");
            logs.setParametro(parametro);
            logs.setValor(valor);

            this.logsServico.cadastrarLog(logs);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public SipAccountProvisioning getSipAccountProvisioning() {
        return sipAccountProvisioning;
    }

    public void setSipAccountProvisioning(SipAccountProvisioning sipAccountProvisioning) {
        this.sipAccountProvisioning = sipAccountProvisioning;
    }

    public SipAccountProvisioningHolder getSipAccountProvisioningHolder() {
        return sipAccountProvisioningHolder;
    }

    public void setSipAccountProvisioningHolder(SipAccountProvisioningHolder sipAccountProvisioningHolder) {
        this.sipAccountProvisioningHolder = sipAccountProvisioningHolder;
    }
}
