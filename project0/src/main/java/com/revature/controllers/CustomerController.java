package com.revature.controllers;

import java.util.Scanner;

import com.revature.services.SkiServices;

public class CustomerController {
	
	private static SkiServices ss = new SkiServices();
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
				System.out.println("Shop Inventory");
				flag = false;
				shopInventory();
				break;
			case "2":
				// View Cart
				System.out.println("Viewing Your Cart");
				flag = false;
				viewCart();
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
				System.out.println("Invalid input, please select an option 1-4.");
				System.out.println();
			}
		}
	}
	
	public static void shopInventory() {
		ss.showInventory();
		System.out.println("Please Enter the Item Number for the Skis you would like to Purchase");
		System.out.println("Or Press 0 to Return to the Customer Dashboard:");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 0){
			customerDashboard();
		} else {
			ss.moveToCart(choice);
		}
	}
	
	public static void viewCart() {
		
	}
	
	public static void orderHistory() {
		
	}
	
}
