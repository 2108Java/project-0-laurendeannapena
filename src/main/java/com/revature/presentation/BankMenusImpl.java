package com.revature.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.service.BankServices;

public class BankMenusImpl implements BankMenus{

	BankServices service;
	
	public BankMenusImpl(BankServices service) {
		this.service = service;
	}
	
	public void display() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1) Login to existing account.");
		System.out.println("2) Register for new account.");
		String userChoice = sc.nextLine();
		
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
		}//end switch statement
		
	}
	
	private void getMenu(User currentUser, Scanner sc) {
		String menuType = currentUser.getUserType();
		
		if(menuType.equals("customer")) {
			customerMenu(currentUser, sc);
		}
		else if(menuType.equals("employee")){
			employeeMenu(currentUser, sc);
		}//end if statement
		
	}//end method getMenu

	private void employeeMenu(User currentUser, Scanner sc) {
		System.out.println("This has yet to be implemented.");
		
	}

	private void customerMenu(User currentUser, Scanner sc) {
		boolean running = true;
		while(running) {
			System.out.println("1. Create new account.");
			System.out.println("2. Apply for joint account.");
			System.out.println("3. View account balances.");
			System.out.println("4. Make a withdrawal.");
			System.out.println("5. Make a deposit.");
			System.out.println("6. Transfer money to another user.");
			System.out.println("7. View pending money transfers.");
			System.out.println("0. Logout.");
			
			String choice = sc.nextLine();
			
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
				accountDisplay(service.listAccounts(userAccounts, currentUser));
				break;
			case "4":
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
				break;
			case "7":
				break;
			case "0":
				running = false;
				break;
			default:
				System.out.println("Please select a valid optiion.");
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
		
	}//end method accountDisplay

	public User loginMenu(Scanner sc) {
		System.out.println("Username: ");
		String username = sc.nextLine();
		
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		boolean login = service.authenticate(username, password);
		User currentUser = null;
		
		if(login) {
			System.out.println("Login succeeded.");
			currentUser = service.getUserByUsername(username);
		}
		else {
			System.out.println("Login attempt failed. Please try again.");
		}//end if statement
		return currentUser;
	}//end method loginMenu
	
	public User registrationMenu(Scanner sc) {
		//get user input for account creation
		System.out.println("Username: ");
		String username = sc.nextLine();
		
		System.out.println("Password: ");
		String user_password = sc.nextLine();
		
		System.out.println("First name: ");
		String first_name = sc.nextLine();
		
		System.out.println("Last name: ");
		String last_name = sc.nextLine();
		
		System.out.println("Are you an employee?(y/n): ");
		String user_type = sc.nextLine();
		
		//check user input to determine user type
		if(user_type.equals("y")) {
			user_type = "employee";
			//System.out.println("I am an employee!");
		}
		else if(user_type.equals("n")) {
			user_type = "customer";
			//System.out.println("I am a customer!");
		}//end if statement
		
		//create new user
		User newUser = new User(username, user_password, first_name, last_name, user_type);
		
		
		if(service.addUser(newUser)) {
			System.out.println("User created successfully.");
			newUser = service.getUserByUsername(username);
		}
		else {
			System.out.println("User not created.");
		}//end if statement
		return newUser;
	}//end method registrationMethod

}