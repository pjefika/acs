package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.wifiInfo.WifiInfoHolder;
import util.JSFUtil;

public class WiFiInfoAction {

	public WifiInfoHolder[] getWiFiInfo(Integer deviceId, String autenticacao) throws Exception {

		/**
		 * Busca informações do wifi no modem.
		 **/

		Client client = Client.create();

		String url = JSFUtil.acs() + "capability/execute?capability=" + URLEncoder.encode("\"getLanWiFiInfo\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode("{\"frequency\":\"2.4GHz\"}", "UTF-8");

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		WifiInfoHolder[] wifiInfoHolders = gson.fromJson(output, WifiInfoHolder[].class);

		clientResponse.close();

		return wifiInfoHolders;

	}
	
}
