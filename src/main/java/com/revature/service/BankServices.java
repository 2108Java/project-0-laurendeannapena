package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;

public interface BankServices {

	boolean authenticate(String username, String password);

	User getUserByUsername(String username);

	boolean addUser(User newUser);

	boolean createNewAccount(User currentUser, Scanner sc);

	List<Account> listAccounts(List<Account> userAccounts, User currentUser);

	boolean depositFunds(User currentUser, Scanner sc);

}