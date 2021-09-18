package com.revature.service;

import com.revature.models.User;
import com.revature.repo.BankDAO;

public class BankServicesImpl implements BankServices{
	
	BankDAO database;
	
	public BankServicesImpl(BankDAO database) {
		this.database = database;
	}
	
	//authenticate a user attempting to login
	public boolean authenticate(String username, String password) {
		//before checking condition success is false
		boolean success = false;
			
		//identify the user based on their provided username
		User currentUser = getUserByUsername(username);
		
		//make sure the user and their password are not null
		if(currentUser != null && currentUser.getUserPassword() != null) {
			//make the entered password matches the stored password
			success = currentUser.getUserPassword().equals(password);			
		}
		
		return success;
	}

	//retrieve stored user information using a provided username
	public User getUserByUsername(String username) {
		
		User currentUser = database.selectUserByUsername(username);
		
		return currentUser;
	}

	@Override
	public boolean addUser(User newUser) {
		// TODO Auto-generated method stub
		return database.insertUser(newUser);
	}
}
