package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.getInfo.InfoHolder;
import entidades.sys.Autenticacao;
import util.JSFUtil;

public class DeviceInfoAction {

	public InfoHolder getDeviceInfo(Integer deviceId, Autenticacao autenticacao) throws Exception {

		try {

			Client client = Client.create();

			String url = autenticacao.getLink() + "capability/diagnostic?diagnostic="+ URLEncoder.encode("\"getDeviceInfo\"", "UTF-8") +"&deviceId=" + deviceId;

			WebResource webResource = client.resource(url);

			ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);

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

}
