package service;

public class ErrorService {
	
	private String error = "";

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isEmpty() {
		
		return getError().isEmpty();
	}
}
