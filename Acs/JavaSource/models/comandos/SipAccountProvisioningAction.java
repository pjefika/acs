package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.sip.SipAccountProvisioning;
import entidades.sip.SipAccountProvisioningHolder;
import util.JSFUtil;

public class SipAccountProvisioningAction {

	public SipAccountProvisioningHolder sipAccountProvisioning(Integer deviceId, SipAccountProvisioning sipAccountProvisioning, String autenticacao) throws Exception {

		/**
		 * Aciona o FXS 
		 **/

		Client client = Client.create();	

		String url = JSFUtil.acs() + "capability/execute?capability=" + URLEncoder.encode("\"sipAccountProvisioning\"", "UTF-8") + "&deviceId="+ deviceId +"&input=" + URLEncoder.encode("{\"DirectoryNumber\":\"" + sipAccountProvisioning.getDirectoryNumber() + "\",\"AuthUserName\":\"" + sipAccountProvisioning.getAuthUserName() + "\",\"AuthPassword\":\"" + sipAccountProvisioning.getAuthPassword() + "\",\"ProxyServer\":\"" + sipAccountProvisioning.getProxyServer() + "\",\"RegistrarServer\":\"" + sipAccountProvisioning.getRegistrarServer() + "\",\"UserAgentDomain\":\"" + sipAccountProvisioning.getUserAgentDomain() + "\",\"OutboundProxy\":\"" + sipAccountProvisioning.getOutboundProxy() + "\",\"PhyReferenceList\":\"" + sipAccountProvisioning.getPhyReferenceList() + "\"}", "UTF-8");

		System.out.println(url);
		
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		SipAccountProvisioningHolder sipAccountProvisioningHolder = gson.fromJson(output, SipAccountProvisioningHolder.class);

		clientResponse.close();

		return sipAccountProvisioningHolder;

	}
	
}
