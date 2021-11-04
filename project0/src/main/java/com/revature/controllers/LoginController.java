package com.revature.controllers;

import java.util.Scanner;

import com.revature.exceptions.LoginException;
import com.revature.services.CustomerService;
import com.revature.services.EmployeeService;

public class LoginController {
	
	private static CustomerService cs = new CustomerService();
	private static EmployeeService es = new EmployeeService();
	private static Scanner sc = new Scanner(System.in);
	
	public static void userLogin() {
		System.out.println("Press 1 for customer, 2 for employee:");
		String user = sc.nextLine();
		
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		// check to see if email/user exists
		
		System.out.println("Please Enter Your Password:");
		String password = sc.nextLine();
		// check to see if password matches email and is correct
		
		if(user.equals("1")) {
			try {
				cs.login(email, password);
				System.out.println("Log In Successful!");
				CustomerController.customerDashboard();
			} catch (LoginException e) {
				System.out.println("Invalid Login, Press 1 to Try Again or Press 2 Register for an Account:");
				String input = sc.nextLine();
				if(input.equals("1")) {
					userLogin();
				} else {
					RegisterController.registrationMode();
				}
			}
		}
		if(user.equals("2")) {
			try {
				es.login(email, password);
				EmployeeController.employeeDashboard();
			} catch (LoginException e) {
				System.out.println("Invalid Login, Press 1 to Try Again or Press 2 Register for an Account:");
				String input = sc.nextLine();
				if(input.equals("1")) {
					userLogin();
				} else {
					RegisterController.registrationMode();
				}
			}
		}
		else {
			userLogin();
		}
		
		
		// display success + welcome message 
		
		// if email is customer display customer dashboard
		// if email is employee display employee dashboard
			// if email is owner display owner dashboard

		
	}
}
