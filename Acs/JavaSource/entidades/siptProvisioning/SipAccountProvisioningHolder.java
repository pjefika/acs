package entidades.siptProvisioning;

public class SipAccountProvisioningHolder {

	private String idCommand;
	
	private Integer statusCode;
	
	private String exception;

	public String getIdCommand() {
		return idCommand;
	}

	public void setIdCommand(String idCommand) {
		this.idCommand = idCommand;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
}
