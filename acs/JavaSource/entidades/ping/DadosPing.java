package entidades.ping;

public class DadosPing {
	
	private String hostAdress = "www.google.com";
	
	private Integer numberRepetitions = 4;

	public String getHostAdress() {
		return hostAdress;
	}

	public void setHostAdress(String hostAdress) {
		this.hostAdress = hostAdress;
	}

	public Integer getNumberRepetitions() {
		return numberRepetitions;
	}

	public void setNumberRepetitions(Integer numberRepetitions) {
		this.numberRepetitions = numberRepetitions;
	}
	
}
