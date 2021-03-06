package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.Account;
import com.revature.models.MoneyTransfer;
import com.revature.models.User;
import com.revature.util.DBConnection;

public class BankDAOImpl implements BankDAO{

	final static Logger loggy = Logger.getLogger(BankDAOImpl.class);
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
			loggy.error("SQL exception occurred or database connection failed.");
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
			loggy.error("SQL exception occurred or database connection failed.");
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
			String sql = "INSERT INTO accounts(first_user, second_user, account_type, account_balance, is_approved) VALUES (?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, newAccount.getUserIdOne());
			ps.setInt(2, newAccount.getUserIdTwo());
			ps.setString(3, newAccount.getAccountType());
			ps.setDouble(4, newAccount.getAccountBalance());
			ps.setBoolean(5, newAccount.isApproved());
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}

		
		return success;
	}

	@Override
	public List<Account> queryAccountsByUserId(List<Account> userAccounts, int id) {
		
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM accounts WHERE is_approved = ? AND (first_user = ? OR second_user = ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			ps.setInt(3, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userAccounts.add(new Account(
						rs.getInt("account_id"),
						rs.getInt("first_user"),
						rs.getInt("second_user"),
						rs.getString("account_type"),
						rs.getDouble("account_balance"),
						rs.getBoolean("is_approved")
						));
			}//end while loop
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		
		return userAccounts;
	}//end method queryAccountsByUserId

	@Override
	public boolean updateAccountBalance(int accountId, double accountBalance) {
		boolean success = false;
		
		try{
			//1. Connect to database!
			Connection connection = dbConnection.getConnection();
			
			//2. Write a SQL statement String
			String sql = "UPDATE accounts SET account_balance = ? WHERE account_id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, accountBalance);
			ps.setInt(2, accountId);
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}

		
		return success;
	}

	@Override
	public List<Account> selectPendingAccounts(List<Account> pendingAccounts) {
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM accounts WHERE is_approved = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, false);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pendingAccounts.add(new Account(
						rs.getInt("account_id"),
						rs.getInt("first_user"),
						rs.getInt("second_user"),
						rs.getString("account_type"),
						rs.getDouble("account_balance"),
						rs.getBoolean("is_approved")
						));
			}//end while loop
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		
		return pendingAccounts;
	}

	@Override
	public boolean deleteAccountById(int accountId) {
		boolean success = false;
		
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "DELETE FROM accounts WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, accountId);
			
			ps.execute();
			
			success = true;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		
		return success;
	}//end method deleteAccountById

	@Override
	public boolean updateAccountById(int accountId) {
		boolean success = false;
		
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "UPDATE accounts SET is_approved = ? WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, accountId);
			
			ps.execute();
			
			success = true;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		
		return success;
	}//end method updateAccountById

	@Override
	public List<User> selectCustomers() {
		List<User> userList = new ArrayList<>();
		
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM users WHERE user_type = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "customer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userList.add(new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("user_password"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("user_type")
						));
			}//end while loop
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		
		return userList;
	}

	@Override
	public boolean transferFunds(MoneyTransfer transfer) {
		boolean success = false;
		
		try{
			//1. Connect to database!
			Connection connection = dbConnection.getConnection();
			
			//2. Write a SQL statement String
			String sql = "INSERT INTO pending_transfers(from_account, to_account, transfer_amount) VALUES (?,?,?) ";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, transfer.getFromAccount());
			ps.setInt(2, transfer.getToAccount());
			ps.setDouble(3, transfer.getTransferAmount());
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}

		return success;
	}

	@Override
	public List<MoneyTransfer> getTransfers(List<MoneyTransfer> pendingTransfers) {
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM pending_transfers";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pendingTransfers.add(new MoneyTransfer(
						rs.getInt("transfer_id"),
						rs.getInt("from_account"),
						rs.getInt("to_account"),
						rs.getDouble("transfer_amount")
						));
			}//end while loop
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		return pendingTransfers;
	}

	@Override
	public boolean deleteTransferById(int transferId) {
		boolean success = false;
		
		try{
			Connection connection = dbConnection.getConnection();
			
			String sql = "DELETE FROM pending_transfers WHERE transfer_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, transferId);
			
			ps.execute();
			
			success = true;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			loggy.error("SQL exception occurred or database connection failed.");
		}//end try catch
		
		return success;
	}


}
