package models.comandos;

import java.net.URLEncoder;

import javax.ejb.EJB;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.reboot.RebootHolder;
import entidades.sys.UsuarioEfika;
import models.sys.LogsServico;
import util.JSFUtil;

public class RebootAction {
	
	@EJB
	private LogsServico logsServico;
	
	public RebootHolder Reboot(Integer deviceId, String autenticacao, UsuarioEfika usuarioEfika, String parametro) throws Exception {

		/**
		 * Realiza o reset padrão no Equipamento. 
		 **/

		Client client = Client.create();

		String url = JSFUtil.acs() + "capability/execute?capability="+ URLEncoder.encode("\"Reboot\"", "UTF-8") +"&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", autenticacao).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		RebootHolder rebootHolder = gson.fromJson(output, RebootHolder.class);

		clientResponse.close();

		return rebootHolder;

	}
	
}
