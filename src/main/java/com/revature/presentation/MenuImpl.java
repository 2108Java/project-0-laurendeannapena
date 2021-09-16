package com.revature.presentation;

import java.util.Scanner;

import com.revature.models.User;
import com.revature.service.BankServices;

public class MenuImpl implements Menu {

	BankServices service;
	final Scanner sc = new Scanner(System.in);
	
	public MenuImpl(BankServices service) {
		this.service = service;
	}
	public void loginOrRegister() {
		
		
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
		}
		else {
			System.out.println("User not created.");
		}//end if statement
		
		
	}//end method loginOrRegister
	
	public void customerMenu(){
		
		boolean running = true;
		
		while(running) {
			System.out.println("1. Create another account.");
			System.out.println("2. Check account balance.");
			System.out.println("3. Withdrawl funds.");
			System.out.println("4. Deposit funds.");
			System.out.println("5. Transfer funds to another customer.");
			System.out.println("6. Accept a transfer of funds from another customer.");
			System.out.println("7. Logout.");
			
			String menuChoice = sc.nextLine();
			
			switch(menuChoice) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				break;
			case "7":
				running = false;
				break;
			
			}//end switch statement
					
		}//end while loop
	}//end method customerMenu
	
	public void employeeMenu() {

		boolean running = true;
		
		while(running) {
			System.out.println("1. View pending account registration.");
			System.out.println("2. Approve account registration.");
			System.out.println("3. Reject account registration.");
			System.out.println("4. View customer accounts.");
			System.out.println("5. View transaction log.");
			System.out.println("6. Logout.");
			
			String menuChoice = sc.nextLine();
			
			switch(menuChoice) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				running = false;
				break;
			
			}//end switch statement
					
		}//end while loop
	}//end method employeeMenu
}
