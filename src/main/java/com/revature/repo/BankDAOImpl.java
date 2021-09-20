package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.DBConnection;

public class BankDAOImpl implements BankDAO{

	DBConnection dbConnection;
	
	public BankDAOImpl(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	@Override
	public User selectUserByUsername(String username) {
		
		User requestedUser = new User();
		
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				requestedUser.setId(rs.getInt("user_id"));
				requestedUser.setUsername(rs.getString("username"));
				requestedUser.setUserPassword(rs.getString("user_password"));
				requestedUser.setFirstName(rs.getString("first_name"));
				requestedUser.setLastName(rs.getString("last_name"));
				requestedUser.setUserType(rs.getString("user_type"));
			}//end while loop
		}
		catch(SQLException e) {
			e.printStackTrace();
		}//end try catch
		return requestedUser;
	}//end method selectUserByUsername

	
	@Override
	public boolean insertUser(User newUser) {
		boolean success = false;
		
		try{
			//1. Connect to database!
			Connection connection = dbConnection.getConnection();
			
			//2. Write a SQL statement String
			String sql = "INSERT INTO users(username, user_password, first_name, last_name, user_type) VALUES (?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getUserPassword());
			ps.setString(3, newUser.getFirstName());
			ps.setString(4, newUser.getLastName());
			ps.setString(5, newUser.getUserType());
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return success;
	}//end method insertUser

	@Override
	public boolean insertAccount(Account newAccount) {
		boolean success = false;
		
		try{
			//1. Connect to database!
			Connection connection = dbConnection.getConnection();
			
			//2. Write a SQL statement String
			String sql = "INSERT INTO accounts(user_id, account_type, account_balance, is_approved) VALUES (?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, newAccount.getUserIdOne());
			ps.setString(2, newAccount.getAccountType());
			ps.setDouble(3, newAccount.getAccountBalance());
			ps.setBoolean(4, newAccount.isApproved());
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return success;
	}

}
