package com.cognizant.Authorization.exception;

import java.util.Date;

public class ExceptionResponse {
	private String message;
	private Date timestamp;
	
	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}

	public ExceptionResponse(String message, Date timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [message=" + message + ", timestamp=" + timestamp
				+ "]";
	}
	
	
}
