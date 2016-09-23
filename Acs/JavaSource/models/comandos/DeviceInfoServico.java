package models.comandos;

import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.getInfo.InfoHolder;

public class DeviceInfoServico {

	/**
	 * Mudar de lugar a autenticação dps....
	 **/	
	String username = "efika_system";
	String password = "Efika@viv0Gvt";
	String userPassword = username + ":" + password;
	String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
	String concat = "Basic " + encoding;

	public InfoHolder getDeviceInfo(Integer deviceId) throws Exception {

		try {

			Client client = Client.create();

			String url = "http://10.200.6.150:8080/nbbs/api/capability/diagnostic?diagnostic="+ URLEncoder.encode("\"getDeviceInfo\"", "UTF-8") +"&deviceId=" + deviceId;

			WebResource webResource = client.resource(url);

			ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

			if (clientResponse.getStatus() != 200) {

				throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

			}

			String output = clientResponse.getEntity(String.class);
			
			if (output.equalsIgnoreCase("message\":\"C\u00F3digo de Status inesperado")) {
				
				throw new Exception("Equipamento fora");
				
			}

			Gson gson = new Gson();			

			InfoHolder infoHolder = gson.fromJson(output, InfoHolder.class);

			clientResponse.close();

			return infoHolder;

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

	@SuppressWarnings("unchecked")
	public List<String> listCapabilityNamesFor(Integer deviceId) throws Exception {

		Client client = Client.create();

		String url = "http://10.200.6.150/nbbs/api/capability/listCapabilityNamesFor?deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

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
