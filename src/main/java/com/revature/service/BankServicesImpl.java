package com.revature.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repo.BankDAO;

public class BankServicesImpl implements BankServices{
	
	final static Logger loggy = Logger.getLogger(BankServicesImpl.class);
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
		System.out.println("Enter c for checking account.");
		System.out.println("Enter s for savings account.");
		System.out.println("Enter j for joint account.");
		String accountType = sc.nextLine();
		loggy.info("User input: " + accountType);
		
		switch(accountType) {
		case "c":
			accountType = "checking";
			break;
		case "s":
			accountType = "savings";
			break;
		case "j":
			accountType = "joint";
			break;
		default:
			loggy.warn("User did not input a valid option.");
			System.out.println("Please input c, s, or j.");
		}//end switch statement
		
//		if(accountType.equals("c")) {
//			accountType = "checking";
//		}
//		else if(accountType.equals("s")) {
//			accountType = "savings";
//		}
		loggy.info("Java account object created.");
		Account newAccount = new Account();
		
		if(accountType == "joint") {
			System.out.println("Enter second customer Id: ");
			int secondId = Integer.parseInt(sc.nextLine());
			loggy.info("User input Id: " + secondId);
			
			System.out.println("Enter starting balance: $");
			double balance = Double.parseDouble(sc.nextLine());
			loggy.info("User input balance: " + balance);
			
			loggy.info("Account details passed to new account.");
			newAccount = new Account(currentUser.getId(), secondId, accountType, balance, false);
		}
		else {
			System.out.print("Enter starting balance: $");
			double balance = Double.parseDouble(sc.nextLine());
			loggy.info("User input balance: " + balance);
			
			loggy.info("Account details passed to new account.");
			newAccount = new Account(currentUser.getId(), accountType, balance, false);
		}//end if statement
		
		
		loggy.info("Request to add account to database.");
		boolean success = database.insertAccount(newAccount);
		
		return success;
	}

	//get all of a users approved accounts
	@Override
	public List<Account> listAccountsByUser(List<Account> userAccounts, User currentUser) {
		userAccounts = database.queryAccountsByUserId(userAccounts, currentUser.getId());
		return userAccounts;
	}

	//deposit funds into a user's account
	@Override
	public boolean depositFunds(User currentUser, Scanner sc) {
		boolean success = false;
		List<Account> userAccounts = new ArrayList<>();
		userAccounts = listAccountsByUser(userAccounts, currentUser);
		
		System.out.println("Enter account #");
		int accountId = Integer.parseInt(sc.nextLine());
		
		for(Account currentAccount: userAccounts) {
			if(currentAccount != null && currentAccount.getAccountId() == accountId) {
				System.out.print("Enter deposit amount: $");
				double depositAmount = Double.parseDouble(sc.nextLine());
				if(depositAmount > 0) {
					currentAccount.setAccountBalance(depositAmount);
					success = database.updateAccountBalance(accountId, currentAccount.getAccountBalance());
					break;
				}
			}
		}//end enhanced for loop
		
		return success;
	}

	@Override
	public boolean withdrawalFunds(User currentUser, Scanner sc) {
		boolean success = false;
		List<Account> userAccounts = new ArrayList<>();
		userAccounts = listAccountsByUser(userAccounts, currentUser);
		
		System.out.println("Enter account #");
		int accountId = Integer.parseInt(sc.nextLine());
		
		for(Account currentAccount: userAccounts) {
			if(currentAccount != null && currentAccount.getAccountId() == accountId) {
				System.out.print("Enter withdrawal amount: $");
				double withdrawalAmount = Double.parseDouble(sc.nextLine());
				if(withdrawalAmount > 0) {
					withdrawalAmount = -withdrawalAmount;
					currentAccount.setAccountBalance(withdrawalAmount);
					success = database.updateAccountBalance(accountId, currentAccount.getAccountBalance());
					break;
				}
			}
		}//end enhanced for loop
		
		return success;
	}

	@Override
	public boolean transferMoneyByUsername(User currentUser, Scanner sc) {
		System.out.println("Enter the recipient's username:");
		String transferRecipient = sc.nextLine();
		
		if(getUserByUsername(transferRecipient) != null) {
			System.out.print("Enter transfer amount: $");
			double transferAmount = Double.parseDouble(sc.nextLine());
		}
		else {
			System.out.println("User does not exist.");
		}
		
		return false;
	}

	@Override
	public List<Account> viewPendingAccounts(List<Account> pendingAccounts) {
		
		return database.selectPendingAccounts(pendingAccounts);
	}

	@Override
	public boolean rejectAccountById(int accountId) {
		
		return database.deleteAccountById(accountId);
	}

	@Override
	public boolean approveAccountById(int accountId) {
		
		return database.updateAccountById(accountId);
	}

	@Override
	public void viewTransactionLog() {
		String filePath = "C:\\Users\\laure\\Desktop\\project-0-laurendeannapena\\src\\main\\resources\\project0-log4j.log";
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			
			while((line = in.readLine()) != null) {
				System.out.print(line);
			}//end while loop
			System.out.println("");
			in.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end method viewTransactionLog

	@Override
	public List<Account> listAccountsByUser(List<Account> userAccounts, int userId) {
		userAccounts = database.queryAccountsByUserId(userAccounts, userId);
		return userAccounts;
	}

	@Override
	public List<User> getListOfCustomers() {
		
		return database.selectCustomers();
	}
}
