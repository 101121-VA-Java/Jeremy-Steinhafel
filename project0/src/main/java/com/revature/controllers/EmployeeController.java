package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Skis;
import com.revature.services.SkiServices;

public class EmployeeController {
	
	private static SkiServices ss = new SkiServices();
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
				System.out.println("Add an Item to the Shop:");
				flag = false;
				addItemToShop();
				break;
			case "2":
				// Remove an item from the shop
				System.out.println("Remove an Item from the Shop:");
				flag = false;
				removeItemFromShop();
				break;
			case "3":
				// View Offers
				System.out.println("View Offers For Skis");
				flag = false;
				break;
			case "4":
				// View Payments
				System.out.println("View Payments");
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
	
	public static void addItemToShop() {
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
		employeeDashboard();
	}
	
	public static void removeItemFromShop() {
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
		employeeDashboard();
	}
	
	public static void viewOffers() {
		
	}
	
	public static void viewPayments() {
		
	}
	
	
	
}
