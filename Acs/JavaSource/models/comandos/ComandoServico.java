package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.dados.ResultsHolder;
import entidades.deviceStatus.DeviceStatusHolder;
import entidades.factoryReset.FactoryResetHolder;
import entidades.getInfo.InfoHolder;
import entidades.lanHost.LanHostHolder;
import entidades.reboot.RebootHolder;
import entidades.wifiInfo.SetWiFiConfigHolder;
import entidades.wifiInfo.WifiInfoHolder;

public class ComandoServico {

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

			return resultsHolder;

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

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

			Gson gson = new Gson();			

			InfoHolder infoHolder = gson.fromJson(output, InfoHolder.class);

			return infoHolder;

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

	public RebootHolder Reboot(Integer deviceId) throws Exception {

		Client client = Client.create();

		String url = "http://10.200.6.150:8080/nbbs/api/capability/execute?capability="+ URLEncoder.encode("\"Reboot\"", "UTF-8") +"&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		RebootHolder rebootHolder = gson.fromJson(output, RebootHolder.class);

		return rebootHolder;

	}

	public LanHostHolder[] getLanHostsTable(Integer deviceId) throws Exception {

		Client client = Client.create();

		String url = "http://10.200.6.150:8080/nbbs/api/capability/execute?capability="+ URLEncoder.encode("\"getLanHostsTable\"", "UTF-8") +"&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		LanHostHolder[] lanHostHolders = gson.fromJson(output, LanHostHolder[].class);

		return lanHostHolders;

	}

	public WifiInfoHolder[] getWiFiInfo(Integer deviceId) throws Exception {

		Client client = Client.create();

		String url = "http://10.200.6.150:8080/nbbs/api/capability/execute?capability=" + URLEncoder.encode("\"getLanWiFiInfo\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode("{\"frequency\":\"2.4GHz\"}", "UTF-8");

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		WifiInfoHolder[] wifiInfoHolders = gson.fromJson(output, WifiInfoHolder[].class);

		return wifiInfoHolders;

	}

	public FactoryResetHolder factoryReset(Integer deviceId) throws Exception {

		Client client = Client.create();

		String url = "http://10.200.6.150:8080/nbbs/api/core/device/factoryReset?deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		FactoryResetHolder factoryResetHolder = gson.fromJson(output, FactoryResetHolder.class);

		return factoryResetHolder;

	}

	public DeviceStatusHolder DeviceStatus(Integer deviceId) throws Exception {

		Client client = Client.create();
				
		String url = "http://10.200.6.150/nbbs/api/capability/execute?capability="+ URLEncoder.encode("\"DeviceStatus\"", "UTF-8") +"&deviceId=" + deviceId;

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();
		
		DeviceStatusHolder deviceStatusHolder = gson.fromJson(output, DeviceStatusHolder.class);
		
		return deviceStatusHolder;

	}
	
	public SetWiFiConfigHolder setWiFiConfig(Integer deviceId, String wifiInput, String wifiInputValue) throws Exception {
		
		Client client = Client.create();		
						
		String url = "http://10.200.6.150:8080/nbbs/api/capability/execute?capability=" + URLEncoder.encode("\"setWiFiConfig\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode("{\"" + wifiInput + "\":\"" + wifiInputValue + "\"}", "UTF-8");
		
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);
		
		Gson gson = new Gson();
		
		SetWiFiConfigHolder setWiFiConfigHolder = gson.fromJson(output, SetWiFiConfigHolder.class);
		
		return setWiFiConfigHolder;
		
	}

}
