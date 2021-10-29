package com.revature.controllers;

import java.util.Scanner;

public class CustomerController {

	private static Scanner sc = new Scanner(System.in);	
	
	public static void customerDashboard() {
		// print out menu
		boolean flag = true;
		while(flag){
			System.out.println();
			System.out.println("Customer Dashboard:");
			System.out.println("\n1: View Shop"
								+ "\n2: View Cart"
								+ "\n3: View Order History"
								+ "\n4: Log Out");
			
			String input = sc.nextLine();
			switch(input){
			case "1":
				// view shop method
				System.out.println();
				flag = false;
				break;
			case "2":
				// View Cart
				System.out.println();
				flag = false;
				break;
			case "3":
				// View Order History
				System.out.println();
				flag = false;
				break;
			case "4":
				// Log Out
				WelcomeController.welcomeScreen();
				flag = false;
				break;
			default:
				System.out.println("Invalid input, please select an option 1-4.");
				System.out.println();
			}
		}
	}
	
}
