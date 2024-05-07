package org.hivemq.edge.framework.models;

import com.google.gson.annotations.SerializedName;

public class AuthenticateRequest {
	
	@SerializedName("userName")
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;

}
