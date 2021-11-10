package com.revature.repositories;

import java.util.List;

import com.revature.models.Order;

public interface OrderDao extends GenericDao<Order> {
	Order getByStatus(int customerID, String status);
}
