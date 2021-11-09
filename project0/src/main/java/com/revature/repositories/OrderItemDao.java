package com.revature.repositories;

import java.util.List;

import com.revature.models.OrderItem;

public interface OrderItemDao extends GenericDao<OrderItem> {
	List<OrderItem> getByOrderID(int orderID);
}
