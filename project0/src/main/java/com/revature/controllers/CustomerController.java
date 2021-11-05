package com.revature.controllers;

import java.util.Scanner;

import com.revature.services.SkiServices;

public class CustomerController {
	
	private static SkiServices ss = new SkiServices();
	
	public static void customerDashboard(Scanner sc) {
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
				System.out.println("Shop Our Inventory:");
				flag = false;
				shopInventory(sc);
				break;
			case "2":
				// View Cart
				System.out.println("Viewing Your Cart:");
				flag = false;
				viewCart(sc);
				break;
			case "3":
				// View Order History
				System.out.println("Your Order History");
				orderHistory();
				flag = false;
				break;
			case "4":
				// Log Out
				System.out.println("You Have Successfully Logged Out");
				WelcomeController.welcomeScreen();
				flag = false;
				break;
			default:
				System.out.println("Invalid Input, Please Select an Option 1-4.");
				System.out.println();
			}
		}
	}
	
	
	public static void shopInventory(Scanner sc) {
		System.out.println();
		ss.showInventory();
		System.out.println();
		System.out.println("Please Enter the Item Number For the Skis You Would Like to Add to Your Cart");
		System.out.println("Or Press 0 to Return to the Customer Dashboard:");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 0){
			customerDashboard(sc);
		} else {
			ss.moveToCart(choice);
		}
		
	}
	
	public static void viewCart(Scanner sc) {
		ss.showCart();
		System.out.print("To Submit Your Cart Press 1, To Remove an Item Press 2 Or Press 3 to Return to the Dashboard:");
		String input001 = sc.nextLine();
		if(input001.equals("1")) {
			System.out.println("Cart Submitted");
			ss.submitCart();
		} else if(input001.equals("2")) {
			System.out.println("Enter the Number of the Item You Would Remove");
			int item = sc.nextInt();
			sc.nextLine();
			ss.removeFromCart(item);
			viewCart(sc);
		} else {
			customerDashboard(sc);
		}
	}
	
	public static void orderHistory() {
		
	}
	
}
