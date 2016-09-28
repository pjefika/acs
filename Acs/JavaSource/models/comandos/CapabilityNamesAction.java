package models.comandos;
import java.util.List;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CapabilityNamesAction {
	
	@SuppressWarnings("unchecked")
	public List<String> listCapabilityNamesFor(Integer deviceId, String autenticacao) throws Exception {
		
		Client client = Client.create();

		String url = "http://10.200.6.150/nbbs/api/capability/listCapabilityNamesFor?deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		List<String> values = gson.fromJson(output, List.class);

		clientResponse.close();
		
		return values;


	}

}
