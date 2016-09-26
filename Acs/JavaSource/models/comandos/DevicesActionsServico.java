package models.comandos;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import entidades.dslConnectionInfo.DslConnectionInfoHolder;
import entidades.factoryReset.FactoryResetHolder;
import entidades.interfaceStatics.InterfaceStaticsHolder;
import entidades.lanHost.LanHostHolder;
import entidades.reboot.RebootHolder;
import entidades.siptProvisioning.SipAccountProvisioning;
import entidades.siptProvisioning.SipAccountProvisioningHolder;
import entidades.siptProvisioning.SipDiagnosticsHolder;
import entidades.wifiInfo.WifiInfoHolder;

public class DevicesActionsServico {

	/**
	 * Mudar de lugar a autenticação dps....
	 **/	
	String username = "efika_system";
	String password = "Efika@viv0Gvt";
	String userPassword = username + ":" + password;
	String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
	String concat = "Basic " + encoding;

	public RebootHolder Reboot(Integer deviceId) throws Exception {
		
		/**
		 * Realiza o reset padrão no Equipamento. 
		 **/


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

		clientResponse.close();
		
		return rebootHolder;

	}

	public FactoryResetHolder factoryReset(Integer deviceId) throws Exception {
		
		System.out.println("Factory");

		/**
		 * Realiza o reset de fabrica no Equipamento. 
		 **/

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

		clientResponse.close();
		
		return factoryResetHolder;

	}

	public LanHostHolder[] getLanHostsTable(Integer deviceId) throws Exception {
		/**
		 * Busca os aparelhos que estão conectado ou se conectaram no Modem.
		 **/

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

		clientResponse.close();
		
		return lanHostHolders;

	}

	public WifiInfoHolder[] getWiFiInfo(Integer deviceId) throws Exception {
		
		/**
		 * Busca informações do wifi no modem.
		 **/

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

		clientResponse.close();
		
		return wifiInfoHolders;

	}

	public SipAccountProvisioningHolder sipAccountProvisioning(Integer deviceId, SipAccountProvisioning sipAccountProvisioning) throws Exception {
		
		/**
		 * Aciona o FXS 
		 **/

		Client client = Client.create();	

		String url = "http://10.200.6.150:8080/nbbs/api/capability/execute?capability=" + URLEncoder.encode("\"sipAccountProvisioning\"", "UTF-8") + "&deviceId="+ deviceId +"&input=" + URLEncoder.encode("{\"DirectoryNumber\":\"" + sipAccountProvisioning.getDirectoryNumber() + "\",\"AuthUserName\":\"" + sipAccountProvisioning.getAuthUserName() + "\",\"AuthPassword\":\"" + sipAccountProvisioning.getAuthPassword() + "\",\"ProxyServer\":\"" + sipAccountProvisioning.getProxyServer() + "\",\"RegistrarServer\":\"" + sipAccountProvisioning.getRegistrarServer() + "\",\"UserAgentDomain\":\"" + sipAccountProvisioning.getUserAgentDomain() + "\",\"OutboundProxy\":\"" + sipAccountProvisioning.getOutboundProxy() + "\",\"PhyReferenceList\":\"" + sipAccountProvisioning.getPhyReferenceList() + "\"}", "UTF-8");

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		SipAccountProvisioningHolder sipAccountProvisioningHolder = gson.fromJson(output, SipAccountProvisioningHolder.class);

		clientResponse.close();
		
		return sipAccountProvisioningHolder;

	}

	public SipDiagnosticsHolder sipDiagnostics(Integer deviceId, String phyReferenceList) throws Exception {
				
		/**
		 * Status do Sip
		 **/

		Client client = Client.create();

		String url = "http://10.200.6.150:8080/nbbs/api/capability/diagnostic?diagnostic=" + URLEncoder.encode("\"sipDiagnostics\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode("{\"PhyReferenceList\":\"" + phyReferenceList + "\"}", "UTF-8");

		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		SipDiagnosticsHolder sipDiagnosticsHolder = gson.fromJson(output, SipDiagnosticsHolder.class);

		clientResponse.close();
		
		return sipDiagnosticsHolder;

	}
	
	public InterfaceStaticsHolder interfaceStatics(Integer deviceId) throws Exception {
				
		/**
		 * Busca informações do wifi no modem.
		 **/

		Client client = Client.create();		

		String url = "http://10.200.6.150/nbbs/api/capability/diagnostic?diagnostic=" + URLEncoder.encode("\"getInterfaceStats\"", "UTF-8") + "&deviceId=" + deviceId;
		
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {

			throw new RuntimeException("Failed : HTTP error code : " + clientResponse.getStatus());

		}

		String output = clientResponse.getEntity(String.class);

		Gson gson = new Gson();

		InterfaceStaticsHolder interfaceStaticsHolder = gson.fromJson(output, InterfaceStaticsHolder.class);
				
		clientResponse.close();
		
		return interfaceStaticsHolder;
		
	}
	
	public DslConnectionInfoHolder getDSLConnectionInfo(Integer deviceId) throws Exception {
		
		/**
		 * Busca informações de coneção DSL
		 **/

		Client client = Client.create();
				
		String url = "http://10.200.6.150/nbbs/api/capability/diagnostic?diagnostic=" + URLEncoder.encode("\"getDSLConnectionInfo\"", "UTF-8") + "&deviceId=" + deviceId;
		
		WebResource webResource = client.resource(url);
		
		ClientResponse clientResponse = webResource.accept("application/json").header("Authorization", concat).get(ClientResponse.class);
		
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
