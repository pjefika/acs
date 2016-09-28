package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.lanHost.LanHostHolder;
import util.JSFUtil;

public class LanHostsTableAction {

	public LanHostHolder[] getLanHostsTable(Integer deviceId, String autenticacao) throws Exception {
		/**
		 * Busca os aparelhos que estão conectado ou se conectaram no Modem.
		 **/

		Client client = Client.create();

		String url = JSFUtil.acs() + "capability/execute?capability="+ URLEncoder.encode("\"getLanHostsTable\"", "UTF-8") +"&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		LanHostHolder[] lanHostHolders = gson.fromJson(output, LanHostHolder[].class);

		clientResponse.close();

		return lanHostHolders;

	}

}
