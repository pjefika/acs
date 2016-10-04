package entidades.dados;

import com.google.gson.annotations.SerializedName;

public class ResultsHolder {
	
	private Integer totalLength;

	@SerializedName("results")
	private Results[] results;

	public Results[] getResults() {
		return results;
	}

	public void setResults(Results[] results) {
		this.results = results;
	}
	
	public Integer getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}	

}
