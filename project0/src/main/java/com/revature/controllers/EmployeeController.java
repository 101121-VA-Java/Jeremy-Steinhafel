package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Skis;
import com.revature.services.SkiServices;

public class EmployeeController {
	
	private static SkiServices ss = new SkiServices();
	
	public static void employeeDashboard(Scanner sc) {
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
				System.out.println("Add an Item to the Shop:");
				flag = false;
				addItemToShop(sc);
				break;
			case "2":
				// Remove an item from the shop
				System.out.println("Remove an Item from the Shop:");
				flag = false;
				removeItemFromShop(sc);
				break;
			case "3":
				// View Offers
				System.out.println("View Offers For Skis");
				flag = false;
				viewOffers(sc);
				break;
			case "4":
				// View Payments
				System.out.println("View Payments");
				viewPayments(sc);
				flag = false;
				break;
			case "5":
				// Log Out
				System.out.println("You Have Successfully Logged Out");
				flag = false;
				WelcomeController.welcomeScreen();
				break;
			default:
				System.out.println("Invalid Input, Please Select an Option 1-5.");
				System.out.println();
			}
		
		}
	}
	
	public static void addItemToShop(Scanner sc) {
		System.out.println("Input the brand:");
		String brand = sc.nextLine();
		System.out.println("Input the model:");
		String model = sc.nextLine();
		System.out.println("Input the price:");
		double price = sc.nextDouble();
		sc.nextLine();
		System.out.println("Input the offer status:");
		String offer = sc.nextLine();
		System.out.println("Input the amount in inventory:");
		int inStock = sc.nextInt();
		sc.nextLine();
		Skis s = new Skis(brand, model, price, offer, inStock);
		s = ss.addSkis(s);
		System.out.println("Successfully added " + s.getBrand() + " " + s.getModel() + " " + s.getPrice() + " " + s.getOfferStatus() + " " + s.getInStock());
		employeeDashboard(sc);
	}
	
	public static void removeItemFromShop(Scanner sc) {
		System.out.println("Input the model you would like to remove:");
		String model = sc.nextLine();
		System.out.println("Input the amount in inventory to remove:");
		int inStock = sc.nextInt();
		sc.nextLine();
		Skis s = new Skis(model,inStock);
		s = ss.removeSkis(s);
		System.out.println("Successfully Removed Item.");	
		if(s != null) {
			System.out.println("Product Remaining: " + s.getBrand() + " " + s.getModel() + " " + s.getPrice() + " " + s.getOfferStatus() + " " + s.getInStock());
		}
		employeeDashboard(sc);
	}
	
	public static void viewOffers(Scanner sc) {
		ss.showOffers();
		System.out.println();
		System.out.print("To Accept an Offer Press 1, To Reject an Offer Press 2 Or Press 3 to Return to the Dashboard:");
		System.out.println();
		String input001 = sc.nextLine();
		if(input001.equals("1")) {
			System.out.println("Enter the Number of the Item You Would Accept");
			int item = sc.nextInt();
			sc.nextLine();
			ss.acceptOffer(item);
			viewOffers(sc);
		} else if (input001.equals("2")) {
			System.out.println("Enter the Number of the Item You Would Reject");
			int item = sc.nextInt();
			sc.nextLine();
			ss.rejectOffer(item);
			viewOffers(sc);
		} else {
			employeeDashboard(sc);
		}
	}
	
	public static void viewPayments(Scanner sc) {
		ss.showPayments();
		System.out.print("Press Any Number to Return to the Dashboard:");
		int input001 = sc.nextInt();
		sc.nextLine();
		if(input001 > 0) {
			employeeDashboard(sc);
		} else {
			System.out.println("Invalid Input");
			viewPayments(sc);
		}
		
	}
	
	
	
}
