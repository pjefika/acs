package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.portMapping.PortMappingTableHolder;
import entidades.sys.Autenticacao;
import util.JSFUtil;

public class PortMappingTableAction {
	
	public PortMappingTableHolder getPortMappingTable(Integer deviceId, Autenticacao autenticacao) throws Exception {

		Client client = Client.create();
		
		String url = autenticacao.getLink() + "capability/diagnostic?diagnostic=" + URLEncoder.encode("\"getPortMappingTable\"", "UTF-8") + "&deviceId=" + deviceId;	

		WebResource webResource = client.resource(url);
		
		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		PortMappingTableHolder portMappingTableHolder = gson.fromJson(output, PortMappingTableHolder.class);

		clientResponse.close();

		return portMappingTableHolder;

	}

}
