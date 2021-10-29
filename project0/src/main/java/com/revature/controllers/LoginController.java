package com.revature.controllers;

import java.util.Scanner;

public class LoginController {

	private static Scanner sc = new Scanner(System.in);
	
	public static void userLogin() {
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		// check to see if email/user exists
		
		System.out.println("Please Enter Your Password:");
		String password = sc.nextLine();
		// check to see if password matches email and is correct
		
		System.out.println("Press 1 for customer, 2 for employee:");
		String user = sc.nextLine();
		
		// display success + welcome message 
		System.out.println("Log In Successful! Welcome, ");
		
		// if email is customer display customer dashboard
		// if email is employee display employee dashboard
			// if email is owner display owner dashboard
		if(user.equals("1")) {
			CustomerController.customerDashboard();
		}
		if(user.equals("2")) {
			EmployeeController.employeeDashboard();
		}
		
	}
}
