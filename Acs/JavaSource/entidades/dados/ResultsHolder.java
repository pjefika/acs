package entidades.dados;

import com.google.gson.annotations.SerializedName;

public class ResultsHolder {

	@SerializedName("results")
	private Results[] results;

	public Results[] getResults() {
		return results;
	}

	public void setResults(Results[] results) {
		this.results = results;
	}

}
