package entidades.sip;

public class SipAccountProvisioning {

	private String directoryNumber;
	
	private String authUserName;
	
	private String authPassword;
	
	private String proxyServer;
	
	private String registrarServer;
	
	private String userAgentDomain;
	
	private String outboundProxy;
	
	private String phyReferenceList;

	public String getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(String directoryNumber) {
		this.directoryNumber = directoryNumber;
	}

	public String getAuthUserName() {
		return authUserName;
	}

	public void setAuthUserName(String authUserName) {
		this.authUserName = authUserName;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public String getProxyServer() {
		return proxyServer;
	}

	public void setProxyServer(String proxyServer) {
		this.proxyServer = proxyServer;
	}

	public String getRegistrarServer() {
		return registrarServer;
	}

	public void setRegistrarServer(String registrarServer) {
		this.registrarServer = registrarServer;
	}

	public String getUserAgentDomain() {
		return userAgentDomain;
	}

	public void setUserAgentDomain(String userAgentDomain) {
		this.userAgentDomain = userAgentDomain;
	}

	public String getOutboundProxy() {
		return outboundProxy;
	}

	public void setOutboundProxy(String outboundProxy) {
		this.outboundProxy = outboundProxy;
	}

	public String getPhyReferenceList() {
		return phyReferenceList;
	}

	public void setPhyReferenceList(String phyReferenceList) {
		this.phyReferenceList = phyReferenceList;
	}
		
}
