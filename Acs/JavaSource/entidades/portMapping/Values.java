package entidades.portMapping;

public class Values {
	
	private String protocol;
	
	private Integer intport;
	
	private String status;
	
	private Integer LeaseDuration;
	
	private String intip;
	
	private String portMapName;
	
	private String RemoteAddress;
	
	private Integer extport;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getIntport() {
		return intport;
	}

	public void setIntport(Integer intport) {
		this.intport = intport;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLeaseDuration() {
		return LeaseDuration;
	}

	public void setLeaseDuration(Integer leaseDuration) {
		LeaseDuration = leaseDuration;
	}

	public String getIntip() {
		return intip;
	}

	public void setIntip(String intip) {
		this.intip = intip;
	}

	public String getPortMapName() {
		return portMapName;
	}

	public void setPortMapName(String portMapName) {
		this.portMapName = portMapName;
	}

	public String getRemoteAddress() {
		return RemoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		RemoteAddress = remoteAddress;
	}

	public Integer getExtport() {
		return extport;
	}

	public void setExtport(Integer extport) {
		this.extport = extport;
	}
}
