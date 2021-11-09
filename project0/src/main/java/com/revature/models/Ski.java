package com.revature.models;

public class Ski {

	private String brand;
	private String model;
	private double price;
	private int inStock;
	private int skiID;
	
	public Ski() {
		super();
	}
	
	public Ski(String model, int inStock) {
		super();
		this.model = model;
		this.inStock = inStock;
	}
	
	public Ski(String brand, String model, double price, int inStock, int skiID) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.inStock = inStock;
		this.skiID = skiID;
	}
	
	public Ski(String brand, String model, double price, int inStock) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
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

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public int getSkiID() {
		return skiID;
	}

	public void setSkiID(int skiID) {
		this.skiID = skiID;
	}
	


	
}
