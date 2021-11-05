package com.revature.controllers;

import java.util.Scanner;

// Import Customer Service and Model
import com.revature.models.Customer;
import com.revature.services.CustomerService;

// Import Employee Service and Model
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

// Import Exception used for both Customer and Employee
import com.revature.exceptions.EmailAlreadyExistsException;

public class RegisterController {
	
	private static CustomerService cs = new CustomerService();

	public static void registrationMode(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1: Register as a new Customer"
					+ "\n2: Register as a new Employee"
					+ "\n3: Return to the Main Menu");
			
			// take in user input
			String input = sc.nextLine();
			switch(input){
			case "1":
				// Start Customer Registration
				System.out.println("Customer Registration:");
				System.out.println();
				customerRegistration(sc);
				flag = false;
				break;
			case "2":
				// Start Employee Registration
				System.out.println("Employee Registration:");
				System.out.println();
				employeeRegistration(sc);
				flag = false;
				break;
			case "3":
				// return to main menu
				WelcomeController.welcomeScreen();
				flag = false;
				break;
			default:
				System.out.println("Invalid input, please select an option 1-3.");
				System.out.println();
			}
		}
	}
	
	public static void customerRegistration(Scanner sc) {
		
		System.out.println("Please Enter Your First Name:");
		String firstName = sc.nextLine();
		
		System.out.println("Please Enter Your Last Name:");
		String lastName = sc.nextLine();
		
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		
		String password = passwordCheck(sc);
		
		try {
			Customer c = new Customer(firstName, lastName, email, password);
			cs.addCustomer(c);
		} catch (EmailAlreadyExistsException e) {
			System.out.println("Email is Already in use! Please login or register a different email address...");
		}
		
		System.out.println("Returning to the main menu...");
		WelcomeController.welcomeScreen();
		
		
		
	}

	private static EmployeeService es = new EmployeeService();
	
	public static void employeeRegistration(Scanner sc) {
		
		System.out.println("Please Enter Your Employee ID Number:");
		int employeeID = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Please Enter Your First Name:");
		String firstName = sc.nextLine();
		
		System.out.println("Please Enter Your Last Name:");
		String lastName = sc.nextLine();
		
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		
		String password = passwordCheck(sc);
		
		Employee newEmployee = new Employee(employeeID, firstName, lastName, email, password);
		
		try {
			newEmployee = es.addEmployee(newEmployee);
			System.out.println("Welcome " + newEmployee.getFirstName() + "! Registration Successful");
		} catch (EmailAlreadyExistsException e) {
			System.out.println("An Account with this email already exists. \n Please try to register again or login to your account.");
		}
		
		System.out.println("Returning to the main menu...");
		WelcomeController.welcomeScreen();
		
	}

	public static String passwordCheck(Scanner sc) {
		System.out.println("Please Enter Your Password (Must be at least 8 character long):");
		String password = sc.nextLine();
		if(password.trim().length() < 8) {
			passwordCheck(sc);
		} 
		return password;
	}
}
