package com.example.taskmanager.model;

public class Error {
	private String errorStatus;
	private String message;

	public Error() {
		super();
	}

	public Error(String errorStatus, String message) {
		super();
		this.errorStatus = errorStatus;
		this.message = message;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
