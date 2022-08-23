package com.cognizant.Authorization.exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 109449836231358204L;
	private String message;
	
	public BadRequestException() {
		// TODO Auto-generated constructor stub
	}

	
	
	public BadRequestException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "UserNotFoundException [message=" + message + "]";
	}
	
	
	
	
	
	
}
