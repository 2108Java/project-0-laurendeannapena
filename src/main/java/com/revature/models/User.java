package com.revature.models;

public class User {

	private int id;
	private String username;
	private String userPassword;
	private String firstName;
	private String lastName;
	private String userType;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String userPassword, String firstName, String lastName, String userType) {
		super();
		this.username = username;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}

	public User(int id, String username, String userPassword, String firstName, String lastName, String userType) {
		super();
		this.id = id;
		this.username = username;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}