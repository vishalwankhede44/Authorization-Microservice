package com.cognizant.Authorization.model;

public class JwtRequest {
	
	String username;
	String password;
	
	public JwtRequest() {
		// TODO Auto-generated constructor stub
	}

	public JwtRequest(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	
	
	
}
