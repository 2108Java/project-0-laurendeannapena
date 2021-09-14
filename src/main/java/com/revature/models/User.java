package com.revature.models;

public class User {

	private String username;
	private String user_password;
	private String first_name;
	private String last_name;
	private String user_type;
	
	public User(String username, String user_password, String first_name, String last_name, String user_type) {
		this.username = username;
		this.user_password = user_password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_type = user_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	
}
