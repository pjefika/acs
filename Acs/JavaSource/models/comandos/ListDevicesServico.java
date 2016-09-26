package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.dados.ResultsHolder;

public class ListDevicesServico {	
	
	/**
	 * Mudar de lugar a autenticação dps....
	 **/	
	String username = "efika_system";
	String password = "Efika@viv0Gvt";
	String userPassword = username + ":" + password;
	String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
	String concat = "Basic " + encoding;
	
	public ResultsHolder listDevices(String criteria, String parameter) throws Exception {

		try {
			
			Client client = Client.create();

			String url = "http://10.200.6.150/nbbs/api/core/device/listDevices?offset=0&limit=10&criteria=" + URLEncoder.encode("{\""+criteria+"\":\""+parameter+"\"}", "UTF-8");

			WebResource webResource = client.resource(url);

			ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

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
