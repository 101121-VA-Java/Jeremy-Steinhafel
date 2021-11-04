package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;

public class CustomerList implements CustomerDao {

	private List<Customer> customers;
	
	public CustomerList() {
		customers = new ArrayList<>();
	}
	
	@Override
	public Customer add(Customer t) {
		t.setCustomerID(customers.size());
		customers.add(t);
		return t;
	}
	
	@Override
	public Customer getByID(int id) {
		for(Customer c: customers) {
			if(c.getCustomerID() == id) {
				return c;
			}
		}
		return null;
	}

	
	@Override
	public List<Customer> getAll(){
		return customers;
	}
	
	@Override
	public boolean update(Customer t) {
		Customer temp = getByID(t.getCustomerID());
		if(temp == null || temp.equals(t)) {
			return false;
		}
		customers.set(t.getCustomerID(), t);
		return true;
	}
	
	@Override
	public Customer remove(Customer t) {
		customers.remove(t);
		return t;
	}
	
}
