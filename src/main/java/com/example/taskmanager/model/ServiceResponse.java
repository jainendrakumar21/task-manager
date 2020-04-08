package com.example.taskmanager.model;

public class ServiceResponse<T> {
	private T data;
	private Error error;

	public ServiceResponse() {
		super();
	}

	public ServiceResponse(T data, Error error) {
		super();
		this.data = data;
		this.error = error;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ServiceResponse [data=" + data + ", error=" + error + "]";
	}
}
