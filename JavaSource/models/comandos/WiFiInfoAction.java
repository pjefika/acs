package models.comandos;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import entidades.sys.Autenticacao;
import entidades.wifiInfo.WifiInfoHolder;
import java.net.URLEncoder;
import java.util.Date;
import util.JSFUtil;

public class WiFiInfoAction {

    public WifiInfoHolder[] getWiFiInfo(Integer deviceId, Autenticacao autenticacao, String frequency) throws Exception {

        /**
         * Busca informações do wifi no modem.
         *
         */
        Client client = Client.create();

        String url = autenticacao.getLink() + "capability/execute?capability=" + URLEncoder.encode("\"getLanWiFiInfo\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode(frequency, "UTF-8");

        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", JSFUtil.encodeUser(autenticacao.getUser(), autenticacao.getPassword())).get(ClientResponse.class);

        String output = clientResponse.getEntity(String.class);

        if (clientResponse.getStatus() != 200) {
            if (output.contains("api.capability.execution_error")) {
                Thread.sleep(10000);
                return this.getWiFiInfo(deviceId, autenticacao, frequency);
            } else {
                clientResponse.close();
                throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());
            }

        }

        System.out.println("Output: " + new Date() + "Texto: " + output);

        Gson gson = new Gson();

        WifiInfoHolder[] wifiInfoHolders = gson.fromJson(output, WifiInfoHolder[].class);

        clientResponse.close();

        return wifiInfoHolders;

    }

}
