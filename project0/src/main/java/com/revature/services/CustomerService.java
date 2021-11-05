package com.revature.services;

import java.util.List;

import com.revature.repositories.CustomerDao;
//import com.revature.repositories.CustomerList;
import com.revature.repositories.CustomerPostgres;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.models.Customer;

public class CustomerService {

	private static CustomerDao cd = new CustomerPostgres();
	
	public Customer addCustomer(Customer c) throws EmailAlreadyExistsException{
		Customer newCustomer = this.getCustomerByEmail(c.getEmail());
		if(newCustomer != null) {
			throw new EmailAlreadyExistsException();
		}
		return cd.add(c);
	}
	
	public Customer getCustomerByEmail(String email) {
		List<Customer> customers = cd.getAll();
		for(Customer c : customers) {
			if(c.getEmail().equals(email)) {
				return c;
			}
		}
		return null;
	}
		
	public Customer login(String email, String password) throws LoginException{
		Customer cust = this.getCustomerByEmail(email);
		if(cust == null || !cust.getPassword().equals(password)) {
			throw new LoginException();
		}
		cust.setLoggedIn(true);
		cd.update(cust);
		return cust;
	}
	
	public void logout() {
		List<Customer> customers = cd.getAll();
		for (Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				cust.setLoggedIn(false);
			}
		}
	}
}
