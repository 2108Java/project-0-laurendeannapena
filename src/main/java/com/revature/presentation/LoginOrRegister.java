package com.revature.presentation;

import java.util.Scanner;

import com.revature.models.User;
import com.revature.service.BankServices;

public class LoginOrRegister {

	BankServices service;
	
	public LoginOrRegister(BankServices service) {
		this.service = service;
	}
	public static void Register() {
		
		Scanner sc = new Scanner(System.in);
		
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
		}
		
		
	}
}
