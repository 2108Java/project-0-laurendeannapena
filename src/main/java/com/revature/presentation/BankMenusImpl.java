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
		
		System.out.println("1. Login to existing account.");
		System.out.println("2. Register for new account.");
		String userChoice = sc.nextLine();
		
		loggy.info("User selected" + userChoice);
		
		switch(userChoice) {
		case "1":
			boolean login = true;
			User currentUser = null;
			
			while(login) {
				currentUser = loginMenu(sc);
				if(currentUser != null) {
					login = false;
				}//end if statement
			}//end while loop
			
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
			customerMenu(currentUser, sc);
			loggy.info("Application displays the customer menu.");
		}
		else if(menuType.equals("employee")){
			employeeMenu(currentUser, sc);
			loggy.info("Application displays the employee menu.");
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
				reviewAccountMenu(sc);				
				break;
			case "2":
				break;
			case "3":
				break;
			case "0":
				running = false;
				break;
			default:
				System.out.println("Please select a valid option.");
				loggy.warn("User choice was invalid.");
			}//end switch statement
		}//end while loop
		
	}

	private void reviewAccountMenu(Scanner sc) {
		List<Account> pendingAccounts = new ArrayList<>();
		
		boolean running = true;
		while(running) {
			service.viewPendingAccounts(pendingAccounts);
			accountDisplay(pendingAccounts);
			
			System.out.println("1. Reject account.");
			System.out.println("2. Approve account.");
			System.out.println("0. Exit.");
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1":
				System.out.println("Enter rejected account number:");
				int accountId = Integer.parseInt(sc.nextLine());
				if(service.rejectAccountById(accountId)) {
					System.out.println("Account successfully deleted.");
					loggy.info("Account #" + accountId + "rejected.");
				}
				else {
					System.out.println("Operation failed.");
				}
				break;
			case "2":
				System.out.println("Enter approved account number: ");
				accountId = Integer.parseInt(sc.nextLine());
				if(service.approveAccountById(accountId)) {
					System.out.println("Account approved.");
					loggy.info("Account #" + accountId + "approved.");
				}
				else {
					System.out.println("Account approval failed.");
				}
				break;
			case "0":
				running = false;
				break;
			}//end switch statement
		}//end while loop
		
	}

	private void customerMenu(User currentUser, Scanner sc) {
		boolean running = true;
		while(running) {
			System.out.println("1. Create new account.");
			System.out.println("2. Apply for joint account.");
			System.out.println("3. View account balances.");
			System.out.println("4. Make a withdrawal.");
			System.out.println("5. Make a deposit.");
			System.out.println("6. Transfer money.");
			System.out.println("7. Review pending money transfers.");
			System.out.println("0. Logout.");
			
			String choice = sc.nextLine();
			loggy.info("User entered: " + choice);
			
			switch(choice) {
			case "1":
				if(service.createNewAccount(currentUser, sc)) {
					System.out.println("Account creation complete. Pending employee approval.");
				}
				else{
					System.out.println("Account creation failed.");
				}//end if statement
				break;
			case "2":
				break;
			case "3":
				List<Account> userAccounts = new ArrayList<>();
				accountDisplay(service.listAccountsByUser(userAccounts, currentUser));
				break;
			case "4":
				if(service.withdrawalFunds(currentUser, sc)) {
					System.out.println("Withdrawal successful.");
				}
				else {
					System.out.println("Withdrawal failed.");
				}//end if statement
				break;
			case "5":
				if(service.depositFunds(currentUser, sc)) {
					System.out.println("Deposit successful.");
				}
				else {
					System.out.println("Deposit failed.");
				}//end if statement
				break;
			case "6":
				if(service.transferMoneyByUsername(currentUser, sc)) {
					System.out.println("Money transfer is now pending approval.");
				}
				else {
					System.out.println("Transfer failed.");
				}
				break;
			case "7":
				break;
			case "0":
				running = false;
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
		System.out.println("Username: ");
		String username = sc.nextLine();
		loggy.info("User entered username: "+ username);
		
		System.out.println("Password: ");
		String password = sc.nextLine();
		loggy.info("User entered password: " + password);
		
		boolean login = service.authenticate(username, password);
		User currentUser = null;
		
		if(login) {
			System.out.println("Login succeeded.");
			currentUser = service.getUserByUsername(username);
			loggy.info("User logged in successfully.");
		}
		else {
			System.out.println("Login attempt failed. Please try again.");
			loggy.warn("Login attempt failed.");
		}//end if statement
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