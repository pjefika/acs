package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.getPPPoECredentials.GetPPPoECredentialsHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class GetPPPoECredentials extends AcsAbstractBean {

    private GetPPPoECredentialsHolder getPPPoECredentialsHolder;

    public GetPPPoECredentials() {
        getPPPoECredentialsHolder = new GetPPPoECredentialsHolder();
    }

    public void getPPPoECredentialAction(Integer deviceId) {
        if (isDeviceOnline(deviceId)) {
            try {
                String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getPPPoECredentials.name(), deviceId)).getResult();
                getPPPoECredentialsHolder = (GetPPPoECredentialsHolder) GsonUtil.convert(response, GetPPPoECredentialsHolder.class);
                salvarLog(deviceId, getPPPoECredentialsHolder, EnumCapabilitySimple.getPPPoECredentials.name());
                JSFUtil.addInfoMessage("Credenciais de PPPoE obtidas com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                JSFUtil.addErrorMessage("Erro ao obter credenciais de PPPoE");
            }
        } else {
            JSFUtil.addErrorMessage("Modem inativo.");
        }

    }

    public GetPPPoECredentialsHolder getGetPPPoECredentialsHolder() {
        return getPPPoECredentialsHolder;
    }

    public void setGetPPPoECredentialsHolder(GetPPPoECredentialsHolder getPPPoECredentialsHolder) {
        this.getPPPoECredentialsHolder = getPPPoECredentialsHolder;
    }

}
