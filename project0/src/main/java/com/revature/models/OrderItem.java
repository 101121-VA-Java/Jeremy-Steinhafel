package com.revature.models;

public class OrderItem {
	
	private int orderItemID;
	private int orderID;
	private int skiID;
	private int orderQuantity;

	public OrderItem() {
		super();
	}

	public OrderItem(int orderItemiD, int orderID, int skiID, int orderQuantity) {
		super();
		orderItemID = orderItemiD;
		this.orderID = orderID;
		this.skiID = skiID;
		this.orderQuantity = orderQuantity;
	}

	public OrderItem(int orderID, int skiID, int orderQuantity) {
		super();
		this.orderID = orderID;
		this.skiID = skiID;
		this.orderQuantity = orderQuantity;
	}

	public int getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(int iD) {
		orderItemID = iD;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getSkiID() {
		return skiID;
	}

	public void setSkiID(int skiID) {
		this.skiID = skiID;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
}
