package com.revature.models;

public class Skis {

	private String brand;
	private String model;
	private double price;
	private String offerStatus;
	private int inStock;
	private int customerID;
	
	public Skis() {
		super();
	}
	
	public Skis(String model, int inStock) {
		super();
		this.model = model;
		this.inStock = inStock;
	}
	
	public Skis(String brand, String model, double price, String offerStatus, int inStock) {
		super();
		this.brand = brand;	
		this.model = model;
		this.price = price;
		this.offerStatus = offerStatus;
		this.inStock = inStock;
	}

	public Skis(String brand, String model, double price, String offerStatus, int inStock, int customerID) {
		super();
		this.brand = brand;	
		this.model = model;
		this.price = price;
		this.offerStatus = offerStatus;
		this.inStock = inStock;
		this.customerID = customerID;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	
}
