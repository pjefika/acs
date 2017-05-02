package entidades.sip;

public class SipAccountProvisioning {

    private String DirectoryNumber;

    private String AuthUserName;

    private String AuthPassword;

    private String ProxyServer;

    private String RegistrarServer;

    private String UserAgentDomain;

    private String OutboundProxy;

    private Integer PhyReferenceList = 1;

    public String getDirectoryNumber() {
        return DirectoryNumber;
    }

    public void setDirectoryNumber(String directoryNumber) {
        this.DirectoryNumber = directoryNumber;
    }

    public String getAuthUserName() {
        return AuthUserName;
    }

    public void setAuthUserName(String authUserName) {
        this.AuthUserName = authUserName;
    }

    public String getAuthPassword() {
        return AuthPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.AuthPassword = authPassword;
    }

    public String getProxyServer() {
        return ProxyServer;
    }

    public void setProxyServer(String proxyServer) {
        this.ProxyServer = proxyServer;
    }

    public String getRegistrarServer() {
        return RegistrarServer;
    }

    public void setRegistrarServer(String registrarServer) {
        this.RegistrarServer = registrarServer;
    }

    public String getUserAgentDomain() {
        return UserAgentDomain;
    }

    public void setUserAgentDomain(String userAgentDomain) {
        this.UserAgentDomain = userAgentDomain;
    }

    public String getOutboundProxy() {
        return OutboundProxy;
    }

    public void setOutboundProxy(String outboundProxy) {
        this.OutboundProxy = outboundProxy;
    }

    public Integer getPhyReferenceList() {
        return PhyReferenceList;
    }

    public void setPhyReferenceList(Integer PhyReferenceList) {
        this.PhyReferenceList = PhyReferenceList;
    }

}
