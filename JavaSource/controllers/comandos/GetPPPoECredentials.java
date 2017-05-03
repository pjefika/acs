package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.getPPPoECredentials.GetPPPoECredentialsHolder;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;

@ManagedBean
@RequestScoped
public class GetPPPoECredentials extends AcsAbstractBean {

    private GetPPPoECredentialsHolder getPPPoECredentialsHolder;

    public GetPPPoECredentials() {
        getPPPoECredentialsHolder = new GetPPPoECredentialsHolder();
    }

    public void getPPPoECredentialAction(Integer deviceId) throws IOException {
        String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.getPPPoECredentials.name(), deviceId)).getResult();
        getPPPoECredentialsHolder = (GetPPPoECredentialsHolder) GsonUtil.convert(response, GetPPPoECredentialsHolder.class);
    }

    public GetPPPoECredentialsHolder getGetPPPoECredentialsHolder() {
        return getPPPoECredentialsHolder;
    }

    public void setGetPPPoECredentialsHolder(GetPPPoECredentialsHolder getPPPoECredentialsHolder) {
        this.getPPPoECredentialsHolder = getPPPoECredentialsHolder;
    }

}
