package com.revature.controllers;

import java.util.Scanner;

public class EmployeeController {
	
	private static Scanner sc = new Scanner(System.in);	
	
	public static void employeeDashboard() {
		// print out menu
		boolean flag = true;
		while(flag) {
			System.out.println();
			System.out.println("Employee Dashboard");
			System.out.println("\n1: Add an item to the shop"
								+ "\n2: Remove an item from the shop"
								+ "\n3: View offers for skis"
								+ "\n4: View all payments"
								+ "\n5: Log Out");
			
			String input = sc.nextLine();
			switch(input){
			case "1":
				// Add item
				System.out.println();
				flag = false;
				break;
			case "2":
				// Remove an item from the shop
				System.out.println();
				flag = false;
				break;
			case "3":
				// View Offers
				System.out.println();
				flag = false;
				break;
			case "4":
				// View Payments
				System.out.println();
				flag = false;
				break;
			case "5":
				// Log Out
				System.out.println();
				flag = false;
				break;
			default:
				System.out.println("Invalid input, please select an option 1-5.");
				System.out.println();
			}
		
		}
	}
}
