package com.cognizant.Authorization.model;

public class JwtResponse {
	String token;
	
	public JwtResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
