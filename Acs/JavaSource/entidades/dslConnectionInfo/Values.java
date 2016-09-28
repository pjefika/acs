package entidades.dslConnectionInfo;

import util.JSFUtil;

public class Values {

	private String lossOfLink;
	
	private Float noiseMargin;
	
	private Integer HECErrors;
	
	private String direction;	
	
	private String modulationType;	
	
	private Integer maxRate;
	
	private Long dslShowtimeStart;
	
	private Float attenuation;

	private Float powerLevel;
	
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

	public Float getNoiseMargin() {
		return noiseMargin;
	}

	public void setNoiseMargin(Float noiseMargin) {
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
	
	
	public Long getDslShowtimeStart() {
		return dslShowtimeStart;
	}
	
	public String getDslShowtimeStartStamp() {
		
		return JSFUtil.formatarTimeStampHra(this.dslShowtimeStart);
		
	}

	public void setDslShowtimeStart(Long dslShowtimeStart) {
		this.dslShowtimeStart = dslShowtimeStart;
	}	

	public Float getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Float attenuation) {
		this.attenuation = attenuation;
	}

	public Float getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(Float powerLevel) {
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
