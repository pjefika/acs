package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.dslConnectionInfo.DslConnectionInfoHolder;
import util.JSFUtil;

public class DSLConnectionInfoAction {
	
	public DslConnectionInfoHolder getDSLConnectionInfo(Integer deviceId, String autenticacao) throws Exception {

		/**
		 * Busca informações de conexão ADSL
		 **/

		Client client = Client.create();

		String url = JSFUtil.acs() + "capability/diagnostic?diagnostic=" + URLEncoder.encode("\"getDSLConnectionInfo\"", "UTF-8") + "&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		DslConnectionInfoHolder dslConnectionInfoHolder = gson.fromJson(output, DslConnectionInfoHolder.class);

		clientResponse.close();

		return dslConnectionInfoHolder;


	}

}
