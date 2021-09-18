package com.revature.presentation;

import java.util.Scanner;

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
			break;
		case "2":
			currentUser = registrationMenu(sc);			
			break;
		default:
			System.out.println("Please choose to login or register for an account.");
		}//end switch statement
		
	}
	
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