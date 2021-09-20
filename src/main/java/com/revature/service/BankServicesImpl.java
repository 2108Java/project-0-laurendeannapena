package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
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

	//add a new user
	@Override
	public boolean addUser(User newUser) {
		// TODO Auto-generated method stub
		return database.insertUser(newUser);
	}

	//create a new bank account
	@Override
	public boolean createNewAccount(User currentUser, Scanner sc) {
		System.out.println("Enter c for checking account or s for savings account.");
		String accountType = sc.nextLine();
		
		if(accountType.equals("c")) {
			accountType = "checking";
		}
		else if(accountType.equals("s")) {
			accountType = "savings";
		}
		
		System.out.print("Enter starting balance: $");
		double balance = Double.parseDouble(sc.nextLine());
		
		Account newAccount = new Account(currentUser.getId(), accountType, balance, false);
		
		boolean success = database.insertAccount(newAccount);
		
		return success;
	}

	//get all of a users approved accounts
	@Override
	public List<Account> listAccounts(List<Account> userAccounts, User currentUser) {
		userAccounts = database.queryAccountsByUserId(userAccounts, currentUser.getId());
		return userAccounts;
	}

	//deposit funds into a user's account
	@Override
	public boolean depositFunds(User currentUser, Scanner sc) {
		boolean success = false;
		List<Account> userAccounts = new ArrayList<>();
		userAccounts = listAccounts(userAccounts, currentUser);
		
		System.out.println("Enter account #");
		int accountId = Integer.parseInt(sc.nextLine());
		
		for(Account currentAccount: userAccounts) {
			if(currentAccount != null && currentAccount.getAccountId() == accountId) {
				System.out.print("Enter deposit amount: $");
				double depositAmount = Double.parseDouble(sc.nextLine());
				currentAccount.setAccountBalance(depositAmount);
				success = database.updateAccountBalance(accountId, currentAccount.getAccountBalance());
				break;
			}
		}//end enhanced for loop
		
		return success;
	}
}
