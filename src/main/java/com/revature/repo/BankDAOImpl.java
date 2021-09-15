package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.Transaction;
import com.revature.models.User;



public class BankDAOImpl implements BankDAO{
	
	//Configuration information to connect to our database
		String server = "localhost";
		String url = "jdbc:postgresql://" + server + "/postgres";
		String username = "postgres";
		String password = "Texascaligal21";
		
		
	public boolean insertUser(User newUser) {
		
		boolean success = false;
		
		//1. Connect to database!
		try(Connection connection = DriverManager.getConnection(url,username,password)){
			
			//2. Write a SQL statement String
			
			String sql = "INSERT INTO users(first_name, last_name, username, user_password, user_type) VALUES (?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newUser.getFirst_name());
			ps.setString(2, newUser.getLast_name());
			ps.setString(3, newUser.getUsername());
			ps.setString(4, newUser.getUser_password());
			ps.setString(5, newUser.getUser_type());
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return success;
	}//end insertAccount


	@Override
	public boolean authenticateUser(String username, String user_password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertNewAccount(Account newAccount) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateJointAccount(String username) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double selectAccountBalance(int account_id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean updateAccountBalance(double amount) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Account> selectCustomerAccount(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean insertMoneyTransfer(MoneyTransfer newTransfer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public MoneyTransfer updateMoneyTransfer() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<Transaction> selectTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

}
