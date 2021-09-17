package com.revature.service;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.repo.BankDAO;

public class BankServicesImpl implements BankServices{

	BankDAO database;
	
	public BankServicesImpl(BankDAO database) {
		this.database = database;
	}
	
	@Override
	public boolean authenticateUser(String username, String user_password) {
		
			return database.authenticateUser(username, user_password);
	}

	@Override
	public boolean addUser(User newUser) {
		return database.insertUser(newUser);
	}

	@Override
	public boolean addNewAccount(Account newAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addJointAccount(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double viewAccountBalance(int account_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean changeAccountBalance(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Account> viewCustomerAccount(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postMoneyTransfer(MoneyTransfer newTransfer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MoneyTransfer reviewMoneyTransfer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Transaction> viewTransactions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
