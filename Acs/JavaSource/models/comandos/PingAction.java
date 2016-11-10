package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.ping.PingHolder;
import entidades.sys.Autenticacao;
import util.JSFUtil;

public class PingAction {

	public PingHolder[] pingAction(Integer deviceId, String hostAdress, Autenticacao autenticacao, Integer numberOfRepetitions) throws Exception {

		Client client = Client.create();

		String url = autenticacao.getLink() + "capability/execute?capability="+ URLEncoder.encode("\"Ping\"", "UTF-8") +"&deviceId=" + deviceId + "&input=" + URLEncoder.encode("{\"hostAddress\":\""+hostAdress+"\", \"numberOfRepetitions\":" + numberOfRepetitions + "}", "UTF-8");
		
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		PingHolder[] ping = gson.fromJson(output, PingHolder[].class);

		clientResponse.close();

		return ping;

	}

}
