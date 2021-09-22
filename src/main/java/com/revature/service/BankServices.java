package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
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

	List<Account> viewPendingAccounts(List<Account> pendingAccounts);

	boolean rejectAccountById(int accountId);

	boolean approveAccountById(int accountId);

	void viewTransactionLog();

	List<User> getListOfCustomers();

	boolean transferFunds(int toAccount, int fromAccount, double transferAmount);

	List<MoneyTransfer> viewTransfers(List<Account> accountList, List<MoneyTransfer> pendingTransfers);

	boolean rejectTransfer(int transferId);

	boolean approveTransfer(int transferId, List<MoneyTransfer> pendingTransfers, List<Account> accountList);

}