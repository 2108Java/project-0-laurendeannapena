package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	//database endpoint and login credentials
	private static final String URL = "jdbc:postgresql://localhost/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Texascaligal21";
	
	//create database connection
	public Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		return conn;
		
		
	}
}