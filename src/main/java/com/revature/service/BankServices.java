package com.revature.service;

import com.revature.models.User;

public interface BankServices {

	boolean authenticate(String username, String password);

	User getUserByUsername(String username);

	boolean addUser(User newUser);

}