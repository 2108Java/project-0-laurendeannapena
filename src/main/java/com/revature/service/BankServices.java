package com.revature.service;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.Transaction;
import com.revature.models.User;

public interface BankServices {
	
	//Check user login credentials
	public boolean authenticateUser(String username, String user_password);
	
	//Create new User
	public boolean addUser(User newUser);
	
	//create new checking or savings account
	public boolean addNewAccount(Account newAccount);
	
	//create new joint account
	public boolean addJointAccount(String username);
	
	//view the balance for a specific account
	public double viewAccountBalance(int account_id);
	
	//make a withdrawal or deposit to an account
	public boolean changeAccountBalance(double amount);
	
	//approve or reject pending account registrations
//	public boolean reviewAccountRegistration();
	
	//view a list of a specific customer's accounts
	public ArrayList<Account> viewCustomerAccount(String username);
	
	//create pending money transfer
	public boolean postMoneyTransfer(MoneyTransfer newTransfer);
	
	//approve or reject pending money transfer
	public MoneyTransfer reviewMoneyTransfer();
	
	//view all transactions
	public ArrayList<Transaction> viewTransactions();

}
