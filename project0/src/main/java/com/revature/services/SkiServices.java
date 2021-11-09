package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerPostgres;
import com.revature.repositories.OrderDao;
import com.revature.repositories.OrderItemDao;
import com.revature.repositories.OrderItemPostgres;
import com.revature.repositories.OrderPostgres;
import com.revature.repositories.SkiDao;
//import com.revature.repositories.SkiList;
import com.revature.repositories.SkiPostgres;
import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.models.OrderItem;
import com.revature.models.Ski;
import com.revature.controllers.CustomerController;
import com.revature.exceptions.OutOfStockException;

public class SkiServices {

	private static SkiDao sd = new SkiPostgres();
	private static CustomerDao cd = new CustomerPostgres();
	private static OrderDao od = new OrderPostgres();
	private static OrderItemDao oid = new OrderItemPostgres();
	private static Scanner sc = new Scanner(System.in);

	public Ski addSkis(Ski s) {
		Ski compareSkis = this.getSkisByModel(s.getModel());
		if (compareSkis != null) { // && newSkis.getBrand().equals(s.getBrand())
			int total = compareSkis.getInStock() + s.getInStock();
			s.setInStock(total);
			compareSkis.setInStock(total);
			sd.update(compareSkis);
			return compareSkis;
		} else {
			return sd.add(s);
		}
	}

	public Ski getSkisByModel(String model) {
		List<Ski> skis = sd.getAll();
		for (Ski s : skis) {
			if (s.getModel().equals(model)) {
				return s;
			}
		}
		return null;
	}

	public Ski removeSkis(Ski s) {
		Ski compareSkis = this.getSkisByModel(s.getModel());
		int total = compareSkis.getInStock() - s.getInStock();
		s.setInStock(total);
		s.setBrand(compareSkis.getBrand());
		s.setPrice(compareSkis.getPrice());
		compareSkis.setInStock(total);
		sd.update(compareSkis);
		if (total == 0) {
			sd.remove(s);
			return null;
		} else {
			return s;
		}
	}

	public void showInventory() {
		List<Ski> skis = sd.getAll();
		int counter = 0;
		for (Ski s : skis) {
			counter += 1;
			System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " $" + s.getPrice() + " In Stock: "
					+ s.getInStock());
		}
	}

	public void moveToCart(int choice) {
		List<Ski> skis = sd.getInStock();
		if (choice > skis.size()) {
			System.out.println("Invalid Input, Please Select an Number from the List or 0 to return to the Dashboard:");
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 0) {
				CustomerController.customerDashboard(sc);
			} else {
				moveToCart(choice);
			}
		} else if (choice > 0 && choice < skis.size()) {
			Ski skiChoice = skis.get(choice - 1);
			System.out.println("Added (1) " + skiChoice.getBrand() + " " + skiChoice.getModel() + " to Your Cart");
			int newInStock = skiChoice.getInStock() - 1;
			skiChoice.setInStock(newInStock);
			Customer customerLoggedIn = getLoggedInCustomer();
			int customerID = customerLoggedIn.getCustomerID();
			if (skiChoice.getInStock() == 0) {
				sd.remove(skiChoice);
			} else {
				sd.update(skiChoice);
			}
			// get the order for this customer if it exists, otherwise create a new order
			Order pendingOrder = od.getPending(customerID);
			if (pendingOrder == null) {
				pendingOrder = new Order("Pending", customerID, LocalDate.now());
				od.add(pendingOrder);
			}
			List<OrderItem> orderItems = oid.getByOrderID(pendingOrder.getOrderID());
			OrderItem pendingOrderItem = null;
			for (OrderItem o : orderItems) {
				if (o.getSkiID() == skiChoice.getSkiID()) {
					pendingOrderItem = o;
					break;
				}
			}
			if (pendingOrderItem == null) {
				pendingOrderItem = new OrderItem(pendingOrder.getOrderID(), skiChoice.getSkiID(), 1);
				od.add(pendingOrder);
			} else {
				int newQuantity = pendingOrderItem.getOrderQuantity() + 1;
				pendingOrderItem.setOrderQuantity(newQuantity);
				oid.update(pendingOrderItem);
			}
			System.out.println("Press 1 to Continue Shopping or Press 2 to Return to the Dashboard");
			String input002 = sc.nextLine();
			if (input002.equals("1")) {
				CustomerController.shopInventory(sc);
			} else {
				CustomerController.customerDashboard(sc);
			}
		}

	}

	public void showCart() {
		Customer customerLoggedIn = getLoggedInCustomer(); 
		int customerID = customerLoggedIn.getCustomerID();
		Order order = od.getPending(customerID);
		if(order == null) {
			System.out.println("There Are No Items in Your Cart.");
			return;
		}
		List<OrderItem> orderItems = oid.getByOrderID(order.getOrderID());
		if(orderItems.size() == 0) {
			System.out.println("There Are No Items in Your Cart.");
			return;
		}
		int counter = 0;
		for(OrderItem oi : orderItems) {
			counter += 1;
			Ski s = sd.getByID(oi.getSkiID());
			System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " $" + s.getPrice() + " In Cart: "
					+ s.getInStock());
		}
	}

	public void submitCart() {
		List<Ski> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for (Customer cust : customers) {
			if (cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		for (Ski s : skis) {
			if (s.getOfferStatus().equals("Pending") && s.getCustomerID() == customerLoggedIn.getCustomerID()) {
				s.setOfferStatus("Submitted");
				sd.update(s);
			}
		}
		// CustomerController.customerDashboard(sc);
	}

	public void removeFromCart(int item) {
		List<Ski> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for (Customer cust : customers) {
			if (cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for (Ski s : skis) {
			if (s.getOfferStatus().equals("Pending") && s.getCustomerID() == customerLoggedIn.getCustomerID()) {
				counter += 1;
				if (item == counter) {
					Skis removedSkis = new Skis(s.getBrand(), s.getModel(), s.getPrice(), s.getOfferStatus(),
							s.getInStock(), s.getCustomerID());
					s.setOfferStatus("Available");
					s.setCustomerID(0);
					addSkis(s);
					sd.remove(removedSkis);
					System.out.println("Removed item " + s.getBrand() + " " + s.getModel() + " for $" + s.getPrice()
							+ " from cart.");
				}
			}
		}
	}

	public void showHistory() {
		List<Ski> skis = sd.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for (Customer cust : customers) {
			if (cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for (Ski s : skis) {
			if (s.getOfferStatus().equals("Owned") && s.getCustomerID() == customerLoggedIn.getCustomerID()) {
				counter += 1;
				System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " $" + s.getPrice()
						+ " Purchased: " + s.getInStock());
			}
		}
		System.out.println("Press Any Key to Return to the Dashboard:");
		String input003 = sc.nextLine();
		CustomerController.customerDashboard(sc);
	}

	public void showOffers() {
		List<Ski> skis = sd.getAll();
		int counter = 0;
		for (Ski s : skis) {
			if (s.getOfferStatus().equals("Submitted")) {
				counter += 1;
				System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " Offer = $" + s.getPrice()
						+ " In Cart: " + s.getInStock() + " Customer ID: " + s.getCustomerID());
			}
		}
	}

	public void acceptOffer(int item) {
		List<Ski> skis = sd.getAll();
		int counter = 0;
		for (Ski s : skis) {
			if (s.getOfferStatus().equals("Submitted")) {
				counter += 1;
				if (item == counter) {
					System.out.println("Accepted offer on " + s.getInStock() + " " + s.getBrand() + " " + s.getModel()
							+ " for $" + s.getPrice());
					s.setOfferStatus("Owned");
					s.setPurchaseDate(LocalDate.now());
					sd.update(s);
				}
			}
		}
	}

	public void rejectOffer(int item) {
		List<Ski> skis = sd.getByStatus("Submitted");
		int counter = 0;
		for (Ski s : skis) {
			counter += 1;
			if (item == counter) {
				// Skis removedSkis = new Skis(s.getBrand(), s.getModel(), s.getPrice(),
				// s.getOfferStatus(), s.getInStock(), s.getCustomerID());
				Skis newSkiTotal = addSkis(s);
				sd.update(newSkiTotal);
				System.out.println("Rejected offer on " + s.getBrand() + " " + s.getModel() + "for $" + s.getPrice());
			}
		}
	}

	public void showPayments() {
		List<Ski> skis = sd.getByStatus("Owned");
		double total = 0;
		double weeklyTotal = 0;
		LocalDate today = LocalDate.now();
		LocalDate week;
		int counter = 0;
		for (Ski s : skis) {
			counter += 1;
			System.out
					.println(counter + s.getBrand() + " " + s.getModel() + " " + s.getInStock() + " $" + s.getPrice());
			total += s.getPrice();

		}
		System.out.println("Total Sales = " + total);
	}

	private Customer getLoggedInCustomer() {
		List<Customer> customers = cd.getAll();
		for (Customer cust : customers) {
			if (cust.isLoggedIn() == true) {
				return cust;
			}
		}
		return null;
	}

}
