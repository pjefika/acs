/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.comandos;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import entidades.PreferredFirmwareVersion.PreferredFirmwareVersion;
import entidades.sys.Autenticacao;
import java.net.URLEncoder;
import util.JSFUtil;

/**
 *
 * @author G0034481
 */
public class GetPreferredFirmwareVersionAction {

    public PreferredFirmwareVersion getFirmware(Integer deviceId, Autenticacao autenticacao) throws Exception {

        Client client = Client.create();

        String url = autenticacao.getLink() + "capability/execute?capability=" + URLEncoder.encode("\"getPreferredFirmwareVersion\"", "UTF-8") + "&deviceId=" + deviceId;
        
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);

        if (clientResponse.getStatus() != 200) {

            throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

        }

        String output = clientResponse.getEntity(String.class);
        
        Gson gson = new Gson();
        
        PreferredFirmwareVersion preferredFirmwareVersion = gson.fromJson(output, PreferredFirmwareVersion.class);   
        
        clientResponse.close();
        
        return preferredFirmwareVersion;

    }

}
