package com.revature.service;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.Transaction;
import com.revature.models.User;

public interface BankServices {
	
	//Check user login credentials
	public boolean authenticateUser();
	
	//Create new User
	public boolean addUser(User newUser);
	
	//create new checking or savings account
	public boolean addNewAccount(Account newAccount);
	
	//create new joint account
	public boolean addJointAccount();
	
	//view the balance for a specific account
	public double viewAccountBalance();
	
	//make a withdrawal or deposit to an account
	public boolean changeAccountBalance();
	
	//approve or reject pending account registrations
	public boolean reviewAccountRegistration();
	
	//view a list of a specific customer's accounts
	public ArrayList<Account> viewCustomerAccount();
	
	//create pending money transfer
	public boolean postMoneyTransfer();
	
	//approve or reject pending money transfer
	public MoneyTransfer reviewMoneyTransfer();
	
	//view all transactions
	public ArrayList<Transaction> viewTransactions();

}
