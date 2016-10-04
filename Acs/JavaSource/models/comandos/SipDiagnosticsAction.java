package models.comandos;

import java.net.URLEncoder;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.sip.SipDiagnosticsHolder;
import util.JSFUtil;

public class SipDiagnosticsAction {
	
	public SipDiagnosticsHolder sipDiagnostics(Integer deviceId, String phyReferenceList, String autenticacao) throws Exception {

		/**
		 * Status do Sip
		 **/

		Client client = Client.create();

		String url = JSFUtil.acs() + "capability/diagnostic?diagnostic=" + URLEncoder.encode("\"sipDiagnostics\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode("{\"PhyReferenceList\":\"" + phyReferenceList + "\"}", "UTF-8");

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);
		
		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);
		
		Gson gson = new Gson();

		SipDiagnosticsHolder sipDiagnosticsHolder = gson.fromJson(output, SipDiagnosticsHolder.class);
		
		clientResponse.close();

		return sipDiagnosticsHolder;

	}

}
