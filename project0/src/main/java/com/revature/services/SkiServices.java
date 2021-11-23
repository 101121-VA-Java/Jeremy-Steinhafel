package com.revature.services;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
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
		int total = compareSkis.getInStock() - 1;
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
		List<Ski> skis = sd.getInStock();
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
		} else if (choice > 0 && choice <= skis.size()) {
			Ski skiChoice = skis.get(choice - 1);
			System.out.println("Added (1) " + skiChoice.getBrand() + " " + skiChoice.getModel() + " to Your Cart");
			removeSkis(skiChoice);
			Customer customerLoggedIn = getLoggedInCustomer();
			int customerID = customerLoggedIn.getCustomerID();
			// get the order for this customer if it exists, otherwise create a new order
			Order pendingOrder = od.getByStatus(customerID, "Pending");
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
				pendingOrderItem = new OrderItem(pendingOrder.getOrderID(), skiChoice.getSkiID(), 1, "Pending");
				oid.add(pendingOrderItem);
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
		Order order = od.getByStatus(customerID, "Pending");
		if (order == null) {
			System.out.println("There Are No Items in Your Cart.");
			return;
		}
		List<OrderItem> orderItems = oid.getByOrderID(order.getOrderID());
		if (orderItems.size() == 0) {
			System.out.println("There Are No Items in Your Cart.");
			return;
		}
		int counter = 0;
		for (OrderItem oi : orderItems) {
			counter += 1;
			Ski s = sd.getByID(oi.getSkiID());
			System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " $" + s.getPrice() + " In Cart: "
					+ oi.getOrderQuantity());
		}
	}

	public void submitCart() {
		Customer customerLoggedIn = getLoggedInCustomer();
		int customerID = customerLoggedIn.getCustomerID();
		Order order = od.getByStatus(customerID, "Pending");
		if (order == null) {
			System.out.println("There Are No Items in Your Cart.");
			return;
		}
		order.setOrderStatus("Submitted");
		od.update(order);
	}

	public void removeFromCart(int item) {
		// get customer that is logged in and get cart by customer ID
		Customer customerLoggedIn = getLoggedInCustomer();
		int customerID = customerLoggedIn.getCustomerID();
		Order order = od.getByStatus(customerID, "Pending");
		int counter = 0;
		// if nothing in the cart tell the user and return
		if (order == null) {
			System.out.println("There Are No Items in Your Cart.");
			return;
		}
		// get order items according to order id
		List<OrderItem> orderItems = oid.getByOrderID(order.getOrderID());
		// remove item from order item then add back to skis
		for (OrderItem oi : orderItems) {
			counter += 1;
			if (item == counter) {
				OrderItem removedOrderItem = oi;
				// get skis by id and add them back
				Ski returnedSki = sd.getByID(removedOrderItem.getSkiID());
				addSkis(returnedSki);
				oid.remove(removedOrderItem);
				System.out.println(
						"Removed item " + returnedSki.getBrand() + " " + returnedSki.getModel() + " from cart.");
			}
		}
	}

	public void showHistory() {
		Customer customerLoggedIn = getLoggedInCustomer();
		int customerID = customerLoggedIn.getCustomerID();
		Order order = od.getByStatus(customerID, "Owned");
		// if nothing in history tell the user and return
		if (order == null) {
			System.out.println("You have not made any purchases yet");
			CustomerController.customerDashboard(sc);
			return;
		}
		int counter = 0;
		List<OrderItem> orderItems = oid.getByOrderID(order.getOrderID());
		for (OrderItem oi : orderItems) {
			counter += 1;
			Ski purchasedSki = sd.getByID(oi.getSkiID());
			System.out.println(counter + ": " + purchasedSki.getBrand() + " " + purchasedSki.getModel() + " $"
					+ purchasedSki.getPrice() + " Purchased: " + oi.getOrderQuantity());

		}
		System.out.println("Press Any Key to Return to the Dashboard:");
		String input003 = sc.nextLine();
		CustomerController.customerDashboard(sc);
	}

	public void showOffers() {
		List<Order> orders = od.getAll();
		int counter = 0;
		for (Order o : orders) {
			if (o.getOrderStatus().equals("Submitted")) {
				List<OrderItem> orderItems = oid.getByOrderID(o.getOrderID());
				for (OrderItem oi : orderItems) {
					counter += 1;
					Ski s = sd.getByID(oi.getSkiID());
					System.out.println(counter + ": " + s.getBrand() + " " + s.getModel() + " Offer = $" + s.getPrice()
							+ " In Cart: " + oi.getOrderQuantity() + " Customer ID: " + o.getCustomerID());
				}
			}
		}
	}

	public void acceptOffer(int item) {
		List<Order> orders = od.getAll();
		int counter = 0;
		for (Order o : orders) {
			if (o.getOrderStatus().equals("Submitted")) {
				List<OrderItem> orderItems = oid.getByOrderID(o.getOrderID());
				for (OrderItem oi : orderItems) {
					counter += 1;
					if (item == counter) {
						Ski s = sd.getByID(oi.getSkiID());
						oi.setOrderItemStatus("Accepted");
						oid.update(oi);
						System.out.println("Accepted offer on " + oi.getOrderQuantity() + " " + s.getBrand() + " "
								+ s.getModel() + " for $" + s.getPrice());
						break;
					}
				}
				// if all order items are accepted, update order status to owned
				boolean allAccepted = true;
				for (OrderItem oi : orderItems) {
					if (!oi.getOrderItemStatus().equals("Accepted")) {
						allAccepted = false;
						break;
					}
				}
				if (allAccepted) {
					o.setOrderStatus("Owned");
					o.setPurchaseDate(LocalDate.now());
					od.update(o);
				}
			}
		}
	}

	public void rejectOffer(int item) {
		List<Order> orders = od.getAll();
		int counter = 0;
		for (Order o : orders) {
			if (o.getOrderStatus().equals("Submitted")) {
				List<OrderItem> orderItems = oid.getByOrderID(o.getOrderID());
				for (OrderItem oi : orderItems) {
					counter += 1;
					Ski s = sd.getByID(oi.getSkiID());
					if (item == counter) {
						oi.setOrderItemStatus("Rejected");
						addSkis(s);
						oid.remove(oi);
						System.out.println(
								"Rejected offer on " + s.getBrand() + " " + s.getModel() + "for $" + s.getPrice());
					}

				}

			}

		}
	}

	public void showPayments() {
		List<Order> orders = od.getAll();
		double total = 0;
		double weeklyTotal = 0;
		LocalDate today = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		int weekNumber = today.get(weekFields.weekOfWeekBasedYear());
		int counter = 0;
		for (Order o : orders) {
			if(o.getOrderStatus().equals("Owned")) {
				boolean isCurrentWeek = today.getYear() == o.getPurchaseDate().getYear() && weekNumber == o.getPurchaseDate().get(weekFields.weekOfWeekBasedYear());
				List<OrderItem> orderItems = oid.getByOrderID(o.getOrderID());
				for (OrderItem oi : orderItems) { 
					Ski s = sd.getByID(oi.getSkiID());
					counter += 1;
					System.out.println(counter + " " + s.getBrand() + " " + s.getModel() + " " + oi.getOrderQuantity() + " $" + s.getPrice());
					total += s.getPrice();
					if(isCurrentWeek) {
						weeklyTotal += s.getPrice();
					}
				}
			}
		}
		System.out.println("Total Sales = $" + total);
		System.out.println("Sales From This Week = $" + weeklyTotal);
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
