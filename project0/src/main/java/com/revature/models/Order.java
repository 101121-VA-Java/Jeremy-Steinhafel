package com.revature.models;

import java.time.LocalDate;

public class Order {
	
	private int orderID;
	private String orderStatus;
	private int customerID;
	private LocalDate purchaseDate;
	

	public Order() {
		super();
	}
	
	public Order(int orderID, String orderStatus, int customerID, LocalDate purchaseDate) {
		super();
		this.orderID = orderID;
		this.orderStatus = orderStatus;
		this.customerID = customerID;
		this.purchaseDate = purchaseDate;
	}

	public Order(String orderStatus, int customerID, LocalDate purchaseDate) {
		super();
		this.orderStatus = orderStatus;
		this.customerID = customerID;
		this.purchaseDate = purchaseDate;
	}

	public int getOrderID() {
		return orderID;
	}


	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
