package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;



public class BankDAO {
	
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

}
