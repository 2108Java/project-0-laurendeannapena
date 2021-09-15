package com.revature.repo;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.Transaction;
import com.revature.models.User;

public interface BankDAO {
		
		//Check user login credentials
		public boolean authenticateUser(String username, String user_password);
		
		//insert row in user table
		public boolean insertUser(User newUser);
		
		//insert row in account table
		public boolean insertNewAccount(Account newAccount);
		
		//update row in account table
		public boolean updateJointAccount(String username);
		
		//select an account balance from account table
		public double selectAccountBalance(int account_id);
		
		//update account balance from account table
		public boolean updateAccountBalance(double amount);
		
		//approve or reject pending account registrations
//		public boolean reviewAccountRegistration();
		
		//view all of a user's accounts
		public ArrayList<Account> selectCustomerAccount(String username);
		
		//insert row into pending transfers table
		public boolean insertMoneyTransfer(MoneyTransfer newTransfer);
		
		//update pending transfers and transactions tables
		public MoneyTransfer updateMoneyTransfer();
		
		//view all transactions in transactions table
		public ArrayList<Transaction> selectTransactions();
}
