package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerPostgres;
import com.revature.repositories.SkiDao;
//import com.revature.repositories.SkiList;
import com.revature.repositories.SkiPostgres;
import com.revature.models.Customer;
import com.revature.models.Skis;
import com.revature.controllers.CustomerController;
import com.revature.exceptions.OutOfStockException;


public class SkiServices {

	private static SkiDao sd = new SkiPostgres();
	private static CustomerDao cd = new CustomerPostgres();
	private static Scanner sc = new Scanner(System.in);	
	
	public Skis addSkis(Skis s){
		Skis compareSkis = this.getSkisByModel(s.getModel());
		if(compareSkis != null ){ // && newSkis.getBrand().equals(s.getBrand())
			int total = compareSkis.getInStock() + s.getInStock();
			s.setInStock(total);
			compareSkis.setInStock(total);
			sd.update(compareSkis);
			return compareSkis; 
		} else {
			return sd.add(s);	
		}
			
	}
	
	public Skis getSkisByModel(String model) {
		List<Skis> skis = sd.getAll();
		for(Skis s: skis) {
			if(s.getModel().equals(model)) {
				return s;
			}
		}
		return null;
	}
	
	public Skis removeSkis(Skis s) {
		Skis compareSkis = this.getSkisByModel(s.getModel());
		int total = compareSkis.getInStock() - s.getInStock();
		s.setInStock(total);
		s.setBrand(compareSkis.getBrand());
		s.setPrice(compareSkis.getPrice());
		s.setOfferStatus(compareSkis.getOfferStatus());
		compareSkis.setInStock(total);
		if(total == 0) { 
			sd.remove(s);
			return null;
		} else {
			return s;
		}
	}
	
	public void showInventory() {
		List<Skis> skis = sd.getAll();
		int counter = 0;
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Available")){
				counter += 1;
				System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " $"
						+ s.getPrice() + " In Stock: " + s.getInStock());
			}
		}
	}
	
	public void moveToCart(int choice) {
		List<Skis> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		if(choice > skis.size()) {
			System.out.println("Invalid Input, Please Select an Number from the List or 0 to return to the Dashboard:");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice == 0){
				CustomerController.customerDashboard(sc);
			} else {
				moveToCart(choice);
			}
		} else if(choice > 0 && choice < skis.size()) {
			Skis skiChoice = skis.get(choice - 1);
			System.out.println("Added (1) " + skiChoice.getBrand() + " " + skiChoice.getModel()
					+ " to Your Cart");
			int newInStock = skiChoice.getInStock() - 1;
			skiChoice.setInStock(newInStock);
			for(Customer cust : customers) {
				if(cust.isLoggedIn() == true) {
					customerLoggedIn = cust;
				}
			}
			int cartID = customerLoggedIn.getCustomerID();
			sd.update(skiChoice);
			Skis cartSkis = new Skis(skiChoice.getBrand(), skiChoice.getModel(), skiChoice.getPrice(), 
					 "Pending", 1, cartID);
			sd.add(cartSkis);
		}
		
	}
	
	public void showCart() {
		List<Skis> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Pending") && s.getCustomerID() == customerLoggedIn.getCustomerID()){
				counter += 1;
				System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " $"
						+ s.getPrice() + " In Cart: " + s.getInStock());
			}
		}
	}
	
	public void submitCart() {
		List<Skis> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Pending") && s.getCustomerID() == customerLoggedIn.getCustomerID()){
				s.setOfferStatus("Submitted");
				sd.update(s);
			}
		}
	}
	
	public void removeFromCart(int item) {
		List<Skis> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Pending") && s.getCustomerID() == customerLoggedIn.getCustomerID()){
				counter += 1;
				if(item == counter) {
					s.setOfferStatus("Available");
					s.setCustomerID(0);
					sd.update(s);
					System.out.println("Removed item " + s.getInStock() +  s.getBrand() + " " + s.getModel() + "for $" + s.getPrice()
							+ "from cart.");
				}
			}
		}
	}
	
	public void showOffers() {
		List<Skis> skis = sd.getAll();
		int counter = 0;
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Submitted")){
				counter += 1;
				System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " Offer = $"
						+ s.getPrice() + " In Cart: " + s.getInStock() + " Customer: " + s.getCustomerID());
			}
		}
	}
	
	public void acceptOffer(int item) {
		List<Skis> skis = sd.getAll();
		int counter = 0;
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Submitted")){
				counter += 1;
				if(item == counter) {
					s.setOfferStatus("Owned");
					sd.update(s);
					System.out.println("Accepted offer on " + s.getInStock() +  s.getBrand() + " " + s.getModel() + "for $" + s.getPrice());
				}
			}
		}
	}
	
	public void rejectOffer(int item) {
		List<Skis> skis = sd.getAll();
		int counter = 0;
		for(Skis s: skis) {
			if(s.getOfferStatus().equals("Submitted")){
				counter += 1;
				if(item == counter) {
					s.setOfferStatus("Available");
					s.setCustomerID(0);
					sd.update(s);
					System.out.println("Rejected offer on " + s.getInStock() +  s.getBrand() + " " + s.getModel() + "for $" + s.getPrice());
				}
			}
		}
	}
	
}
