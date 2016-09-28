package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.dados.ResultsHolder;
import util.JSFUtil;

public class ListDevicesServico {
	
	public ResultsHolder listDevices(String criteria, String parameter, String autenticacao) throws Exception {

		try {
			
			Client client = Client.create();

			String url = JSFUtil.acs() + "core/device/listDevices?offset=0&limit=10&criteria=" + URLEncoder.encode("{\""+criteria+"\":\""+parameter+"\"}", "UTF-8");

			WebResource webResource = client.resource(url);

			ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

			if (clientResponse.getStatus() != 200) {

				throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

			}

			String output = clientResponse.getEntity(String.class);

			Gson gson = new Gson();

			ResultsHolder resultsHolder = gson.fromJson(output, ResultsHolder.class);
			
			clientResponse.close();
			
			return resultsHolder;

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

}
