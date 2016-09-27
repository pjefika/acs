package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.reboot.RebootHolder;

public class RebootAction {
	
	public RebootHolder Reboot(Integer deviceId, String autenticacao) throws Exception {

		/**
		 * Realiza o reset padrão no Equipamento. 
		 **/


		Client client = Client.create();

		String url = "http://10.200.6.150:8080/nbbs/api/capability/execute?capability="+ URLEncoder.encode("\"Reboot\"", "UTF-8") +"&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		RebootHolder rebootHolder = gson.fromJson(output, RebootHolder.class);

		clientResponse.close();

		return rebootHolder;

	}

}
