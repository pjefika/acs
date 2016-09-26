package entidades.dslConnectionInfo;

public class Values {

	private String lossOfLink;
	
	private Integer noiseMargin;
	
	private Integer HECErrors;
	
	private String direction;	
	
	private String modulationType;	
	
	private Integer maxRate;
	
	private Integer dslShowtimeStart;
	
	private Integer attenuation;

	private Integer powerLevel;
	
	private Integer FECErrors;
	
	private String lossOfFraming;
	
	private Integer synchRate;
	
	private Integer CRCErrors;
	
	private Integer errorSeconds;

	public String getLossOfLink() {
		return lossOfLink;
	}

	public void setLossOfLink(String lossOfLink) {
		this.lossOfLink = lossOfLink;
	}

	public Integer getNoiseMargin() {
		return noiseMargin;
	}

	public void setNoiseMargin(Integer noiseMargin) {
		this.noiseMargin = noiseMargin;
	}

	public Integer getHECErrors() {
		return HECErrors;
	}

	public void setHECErrors(Integer hECErrors) {
		HECErrors = hECErrors;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getModulationType() {
		return modulationType;
	}

	public void setModulationType(String modulationType) {
		this.modulationType = modulationType;
	}

	public Integer getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Integer maxRate) {
		this.maxRate = maxRate;
	}

	public Integer getDslShowtimeStart() {
		return dslShowtimeStart;
	}

	public void setDslShowtimeStart(Integer dslShowtimeStart) {
		this.dslShowtimeStart = dslShowtimeStart;
	}

	public Integer getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Integer attenuation) {
		this.attenuation = attenuation;
	}

	public Integer getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(Integer powerLevel) {
		this.powerLevel = powerLevel;
	}

	public Integer getFECErrors() {
		return FECErrors;
	}

	public void setFECErrors(Integer fECErrors) {
		FECErrors = fECErrors;
	}

	public String getLossOfFraming() {
		return lossOfFraming;
	}

	public void setLossOfFraming(String lossOfFraming) {
		this.lossOfFraming = lossOfFraming;
	}

	public Integer getSynchRate() {
		return synchRate;
	}

	public void setSynchRate(Integer synchRate) {
		this.synchRate = synchRate;
	}

	public Integer getCRCErrors() {
		return CRCErrors;
	}

	public void setCRCErrors(Integer cRCErrors) {
		CRCErrors = cRCErrors;
	}

	public Integer getErrorSeconds() {
		return errorSeconds;
	}

	public void setErrorSeconds(Integer errorSeconds) {
		this.errorSeconds = errorSeconds;
	}		
	
}
