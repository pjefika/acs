package models.comandos;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.sys.Autenticacao;
import util.JSFUtil;

public class FactoryResetAction {
	
	/*
	 public FactoryResetHolder factoryReset(Integer deviceId, String autenticacao) throws Exception {

		Client client = Client.create();

		String url = JSFUtil.acs() + "core/device/factoryReset?deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		FactoryResetHolder factoryResetHolder = gson.fromJson(output, FactoryResetHolder.class);

		clientResponse.close();

		return factoryResetHolder;

	}
	 */
	
	
	public String factoryReset(Integer deviceId, Autenticacao autenticacao) throws Exception {

		/**
		 * Realiza o reset de fabrica no Equipamento. 
		 **/

		Client client = Client.create();

		String url = autenticacao.getLink() + "core/device/factoryReset?deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		clientResponse.close();

		return output;

	}

}
