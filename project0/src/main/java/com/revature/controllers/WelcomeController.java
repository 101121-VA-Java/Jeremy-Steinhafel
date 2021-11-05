package com.revature.controllers;

import java.util.Scanner;

public class WelcomeController {
	
	public static Scanner sc = new Scanner(System.in);

	public static void welcomeScreen() {
		
		boolean flag = true;
		while(flag) {
			// print out menu
			System.out.println();
			System.out.println("Welcome to Slope Side Ski Shop!");
			System.out.println("\n1: Login with an existing account"
					+ "\n2: Register a new account"
					+ "\n3: Exit Program");
			// take in user input
			String input = sc.nextLine();
			switch(input) {
			case "1":
				System.out.println("Login Below...");
				System.out.println();
				// call login controller method
				LoginController.userLogin(sc);
				flag = false;
				break;
			case "2":
				System.out.println("Register Your Account Below...");
				System.out.println();
				// call register controller method
				RegisterController.registrationMode(sc);
				flag = false;
				break;
			case "3":
				System.out.println("Are you sure you would like to exit the program?");
				flag = returnToWelcome();
				break;
			default:
				System.out.println("Invalid input, please select an option 1-3.");
				System.out.println();
				welcomeScreen();
			}
		}
		sc.close();
	}
	
	public static boolean returnToWelcome() {
		System.out.println("Press 1 to return to the main menu or 2 to terminate.");
		String returnInput = sc.nextLine();
		if(returnInput.equals("1")) {
			welcomeScreen();
			return true;
		} else {
			System.out.println("Successfully Exited");
			return false;
		}
	}

}