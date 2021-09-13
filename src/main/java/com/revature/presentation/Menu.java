package com.revature.presentation;

import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner(System.in);
	
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
	}
	
}//end class Menu
