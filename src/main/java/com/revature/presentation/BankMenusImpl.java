package com.revature.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.service.BankServices;

public class BankMenusImpl implements BankMenus{

	final static Logger loggy = Logger.getLogger(BankMenusImpl.class);
	BankServices service;
	
	public BankMenusImpl(BankServices service) {
		this.service = service;
	}
	
	public void display() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Existing user login.");
		System.out.println("2. New user register.");
		String userChoice = sc.nextLine();
		
		loggy.info("User selected" + userChoice);
		
			switch(userChoice) {
			case "1":
				User currentUser = loginMenu(sc);
				
				//once the user has logged in display the credential specific menu options
				getMenu(currentUser, sc);
				break;
			case "2":
				currentUser = registrationMenu(sc);
				
				//once the user has registered display the credential specific menu options
				getMenu(currentUser, sc);
				break;
			default:
				System.out.println("Please choose to login or register for an account.");
				loggy.warn("User choice was invalid.");
			}//end switch statement
	}
	
	private void getMenu(User currentUser, Scanner sc) {
		String menuType = currentUser.getUserType();
		
		if(menuType.equals("customer")) {
			loggy.info("Application displays the customer menu.");
			customerMenu(currentUser, sc);
		}
		else if(menuType.equals("employee")){
			loggy.info("Application displays the employee menu.");
			employeeMenu(currentUser, sc);
		}//end if statement
		
	}//end method getMenu

	private void employeeMenu(User currentUser, Scanner sc) {
		boolean running = true;
		while(running) {
			System.out.println("1. Review pending account registrations.");
			System.out.println("2. View a customer's accounts.");
			System.out.println("3. View transaction log.");
			System.out.println("0. Logout.");
			
			String choice = sc.nextLine();
			loggy.info("User entered: " + choice);
			
			switch(choice) {
			case "1":
				loggy.info("Review account menu opened.");
				reviewAccountMenu(sc);				
				break;
			case "2":
				loggy.info("Customer selection menu opened.");
				customerSelectionMenu(sc);
				break;
			case "3":
				loggy.info("Transaction log requested.");
				service.viewTransactionLog();
				break;
			case "0":
				running = false;
				loggy.info("User logged out.");
				break;
			default:
				System.out.println("Please select a valid option.");
				loggy.warn("User choice was invalid.");
			}//end switch statement
		}//end while loop
		
	}

	private void customerSelectionMenu(Scanner sc) {
		loggy.info("Request formatted list of customers.");
		displayListOfUsers();
		
		
		System.out.println("Enter a customer Id to view their accounts: ");
		int customerId = Integer.parseInt(sc.nextLine());
		loggy.info("Selected user Id: " + customerId);
		
		List<Account> customerAccounts = new ArrayList<>();
		loggy.info("Request list of customer accounts.");
		customerAccounts = service.listAccountsByUser(customerAccounts, customerId);
		
		accountDisplay(customerAccounts);
	}

	private void displayListOfUsers() {
		List<User> users = new ArrayList<>();
		
		loggy.info("Request list of customers.");
		users = service.getListOfCustomers();
		
		loggy.info("Format and display list of customers.");
		for(User user: users) {
			System.out.println("User Id: " + user.getId());
			System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
		}//end enhanced for loop
		
		System.out.println("");
		
	}

	private void reviewAccountMenu(Scanner sc) {
		List<Account> pendingAccounts = new ArrayList<>();
		
		boolean running = true;
		while(running) {
			loggy.info("Request list of pending accounts.");
			service.viewPendingAccounts(pendingAccounts);
			loggy.info("Request formatted list of pending accounts.");
			accountDisplay(pendingAccounts);
			
			System.out.println("1. Reject account.");
			System.out.println("2. Approve account.");
			System.out.println("0. Exit.");
			String choice = sc.nextLine();
			loggy.info("User input: " + choice);
			
			switch(choice) {
			case "1":
				System.out.println("Enter rejected account number:");
				int accountId = Integer.parseInt(sc.nextLine());
				loggy.info("User input: " + accountId);
				
				if(service.rejectAccountById(accountId)) {
					System.out.println("Account successfully deleted.");
					loggy.info("Account #" + accountId + "rejected.");
				}
				else {
					loggy.warn("Account deletion failed.");
					System.out.println("Operation failed.");
				}
				break;
			case "2":
				System.out.println("Enter approved account number: ");
				accountId = Integer.parseInt(sc.nextLine());
				loggy.info("User input: " + accountId);
				
				if(service.approveAccountById(accountId)) {
					System.out.println("Account approved.");
					loggy.info("Account #" + accountId + "approved.");
				}
				else {
					loggy.warn("Account approval failed.");
					System.out.println("Account approval failed.");
				}
				break;
			case "0":
				loggy.info("Review account menu exited.");
				running = false;
				break;
			}//end switch statement
		}//end while loop
		
	}

	private void customerMenu(User currentUser, Scanner sc) {
		boolean running = true;
		while(running) {
			System.out.println("1. Create new account.");
//			System.out.println("2. Apply for joint account.");
			System.out.println("2. View account balances.");
			System.out.println("3. Make a withdrawal.");
			System.out.println("4. Make a deposit.");
			System.out.println("5. Transfer money.");
			System.out.println("6. Review pending money transfers.");
			System.out.println("0. Logout.");
			
			String choice = sc.nextLine();
			loggy.info("User entered: " + choice);
			
			switch(choice) {
			case "1":
				loggy.info("Request new bank account.");
				if(service.createNewAccount(currentUser, sc)) {
					loggy.info("Bank account is pending approval.");
					System.out.println("Account creation complete. Pending employee approval.");
				}
				else{
					loggy.warn("Bank account creation failed.");
					System.out.println("Account creation failed.");
				}//end if statement
				break;
			case "2":
				List<Account> userAccounts = new ArrayList<>();
				accountDisplay(service.listAccountsByUser(userAccounts, currentUser));
				break;
			case "3":
				if(service.withdrawalFunds(currentUser, sc)) {
					System.out.println("Withdrawal successful.");
				}
				else {
					System.out.println("Withdrawal failed.");
				}//end if statement
				break;
			case "4":
				if(service.depositFunds(currentUser, sc)) {
					System.out.println("Deposit successful.");
				}
				else {
					System.out.println("Deposit failed.");
				}//end if statement
				break;
			case "5":
				if(service.transferMoneyByUsername(currentUser, sc)) {
					System.out.println("Money transfer is now pending approval.");
				}
				else {
					System.out.println("Transfer failed.");
				}
				break;
			case "6":
				break;
			case "0":
				running = false;
				loggy.info("User logged out.");
				break;
			default:
				System.out.println("Please select a valid option.");
				loggy.warn("User choice was invalid.");
			}//end switch statement
		}//end while loop
	}//end method customerMenu

	private void accountDisplay(List<Account> userAccounts) {
		for(Account currentAccount: userAccounts) {
			if(currentAccount != null) {
				System.out.println("Account Number: " + currentAccount.getAccountId());
				System.out.println("Account Balance: " + currentAccount.getAccountBalance());
				System.out.println("Account Type: " + currentAccount.getAccountType() + "\n");
			}//end if statement
		}//end enhanced for loop
		loggy.info("Format and display account information.");
	}//end method accountDisplay

	public User loginMenu(Scanner sc) {
		boolean login = false;
		User currentUser = null;
		
		while(!login) {
			System.out.println("Username: ");
			String username = sc.nextLine();
			loggy.info("User entered username: "+ username);
			
			System.out.println("Password: ");
			String password = sc.nextLine();
			loggy.info("User entered password: " + password);
			
			login = service.authenticate(username, password);
			
			if(login) {
				System.out.println("Login succeeded.");
				loggy.info("User logged in successfully.");
				currentUser = service.getUserByUsername(username);
			}
			else {
				System.out.println("Login attempt failed. Please try again.");
				loggy.warn("Login attempt failed.");
			}//end if statement
		}
		
		return currentUser;
	}//end method loginMenu
	
	public User registrationMenu(Scanner sc) {
		//get user input for account creation
		System.out.println("Username: ");
		String username = sc.nextLine();
		loggy.info("User entered username: " + username);
		
		System.out.println("Password: ");
		String user_password = sc.nextLine();
		loggy.info("User entered password: "+ user_password);
		
		System.out.println("First name: ");
		String first_name = sc.nextLine();
		loggy.info("User entered first name: " + first_name);
		
		System.out.println("Last name: ");
		String last_name = sc.nextLine();
		loggy.info("User entered last name: " + last_name);
		
//		System.out.println("Are you an employee?(y/n): ");
//		String user_type = sc.nextLine();
//		
//		//check user input to determine user type
//		if(user_type.equals("y")) {
//			user_type = "employee";
//			loggy.info("User is an employee.");
//		}
//		else if(user_type.equals("n")) {
//			user_type = "customer";
//			loggy.info("User is a customer.");
//		}
//		else{
//			loggy.warn("User did not select customer or employee.");
//		}//end if statement
		
		//create new user
		User newUser = new User(username, user_password, first_name, last_name, "customer");
		loggy.info("Java User object created.");
		
		
		if(service.addUser(newUser)) {
			System.out.println("User created successfully.");
			newUser = service.getUserByUsername(username);
			loggy.info("Bank user successfully created in database.");
		}
		else {
			System.out.println("User not created.");
			loggy.info("Bank user not created in database.");
		}//end if statement
		return newUser;
	}//end method registrationMethod

}