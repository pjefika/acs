package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.dados.ResultsHolder;
import entidades.sys.Autenticacao;
import util.JSFUtil;

public class ListDevicesServico {

	public ResultsHolder listDevices(String criteria, String parameter, Autenticacao autenticacao) throws Exception {

		try {

			Client client = Client.create();

			String url = autenticacao.getLink() + "core/device/listDevices?offset=0&limit=10&criteria=" + URLEncoder.encode("{\""+criteria+"\":\""+parameter+"\"}", "UTF-8");
                        			
                        System.out.println(url);
                        
			WebResource webResource = client.resource(url);

			ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);
                        
                        
                        
                        if (clientResponse.getStatus() != 200) {

				if (clientResponse.getStatus() == 401) {

					throw new Exception("Erro de autorização, consulte o administrador do sistema.");

				} else {

					throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

				}				

			}

			String output = clientResponse.getEntity(String.class);
                        
                        System.out.println(output);

			Gson gson = new Gson();

			ResultsHolder resultsHolder = gson.fromJson(output, ResultsHolder.class);

			clientResponse.close();

			return resultsHolder;

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

}
