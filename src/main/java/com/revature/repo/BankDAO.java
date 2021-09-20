package com.revature.repo;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface BankDAO {

	User selectUserByUsername(String username);

	boolean insertUser(User newUser);

	boolean insertAccount(Account newAccount);

	List<Account> queryAccountsByUserId(List<Account> userAccounts, int id);

	boolean updateAccountBalance(int accountId, double accountBalance);

	

}

