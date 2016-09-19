package entidades.getInfo;

public class InfoHolder {
	
	private String pivotColumn;
	
	private String status;
	
	private Values[] values;
		
	public String getPivotColumn() {
		return pivotColumn;
	}

	public void setPivotColumn(String pivotColumn) {
		this.pivotColumn = pivotColumn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Values[] getValues() {
		return values;
	}

	public void setValues(Values[] values) {
		this.values = values;
	}	
	
}
