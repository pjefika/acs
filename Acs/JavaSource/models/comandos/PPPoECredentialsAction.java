package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.pppoECredentials.PPPoECredentialsHolder;
import util.JSFUtil;

public class PPPoECredentialsAction {
	
	public PPPoECredentialsHolder getPPPoECredentials(Integer deviceId, String autenticacao, String ppoeConf) throws Exception {
		
		Client client = Client.create();
		
		String url = JSFUtil.acs() + "capability/execute?capability=" + URLEncoder.encode("\"setPPPoECredentials\"", "UTF-8") + "&deviceId=" + deviceId + "&input="  + URLEncoder.encode(ppoeConf, "UTF-8");
		
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();
		
		PPPoECredentialsHolder pPPoECredentialsHolder = gson.fromJson(output, PPPoECredentialsHolder.class);
		
		clientResponse.close();
		
		return pPPoECredentialsHolder;
		
	}

}
