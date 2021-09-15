package com.revature.service;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.Transaction;

public interface BankServices {
	
	public boolean authenticateUser();
	
	//Create new User
	public boolean addUser();
	
	public boolean addNewAccount();
	
	public boolean addJointAccount();
	
	public double viewAccountBalance();
	
	public boolean changeAccountBalance();
	
	public boolean reviewAccountRegistration();
	
	public Account viewCustomerAccount();
	
	public boolean postMoneyTransfer();
	
	public MoneyTransfer reviewMoneyTransfer();
	
	public Transaction[] viewTransactions();

}
