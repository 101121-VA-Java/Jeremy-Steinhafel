package com.revature.controllers;

import java.util.Scanner;

public class WelcomeController {
	
	private static Scanner sc = new Scanner(System.in);

	public static void welcomeScreen() {
		boolean flag = true;
		while(flag) {
			System.out.println("Welcome to the Ski Shop!"
					+ "\n1: Login with an existing account"
					+ "\n2: Register a new account"
					+ "\n3: Exit Program");
			String input = sc.nextLine();
			switch(input) {
			case "1":
				System.out.println("Login Here:");
				break;
			case "2":
				System.out.println("Register Your Account Here:");
				break;
			case "3":
				System.out.println("Successfully Exited");
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
	sc.close();
	}

}