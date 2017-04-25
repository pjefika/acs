package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.sys.Autenticacao;
import entidades.wifiInfo.SetWiFiConfigHolder;
import util.JSFUtil;

public class SetWiFiConfigAction {
	
	public SetWiFiConfigHolder setWiFiConfig(Integer deviceId, Autenticacao autenticacao, String wifiConfig) throws Exception {
		
		Client client = Client.create();
		
		String url = autenticacao.getLink() + "capability/execute?capability=" + URLEncoder.encode("\"setWiFiConfig\"", "UTF-8") + "&deviceId=" + deviceId + "&input="  + URLEncoder.encode(wifiConfig, "UTF-8");
				
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);
						
		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();	
				
		SetWiFiConfigHolder setWiFiConfigHolder = gson.fromJson(output, SetWiFiConfigHolder.class);
				
		clientResponse.close();
		
		return setWiFiConfigHolder;
		
		
	}

}
