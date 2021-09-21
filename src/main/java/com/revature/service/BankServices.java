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

	List<Account> listAccountsByUser(List<Account> userAccounts, User currentUser);
	
	List<Account> listAccountsByUser(List<Account> userAccounts, int userId);

	boolean depositFunds(User currentUser, Scanner sc);

	boolean withdrawalFunds(User currentUser, Scanner sc);

	boolean transferMoneyByUsername(User currentUser, Scanner sc);

	List<Account> viewPendingAccounts(List<Account> pendingAccounts);

	boolean rejectAccountById(int accountId);

	boolean approveAccountById(int accountId);

	void viewTransactionLog();

	List<User> getListOfCustomers();

}