package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.services.CustomerService;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.exceptions.LoginException;
import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerPostgres;


public class CustomerServicesTest {

	private static CustomerService cs = new CustomerService();
	private static CustomerDao cd = new CustomerPostgres();
	
//	@BeforeAll
//	public void beforeEach() {
//		cs = new CustomerService();
//	}
	
	@Test
	public void addCustomerCheck() throws EmailAlreadyExistsException {
		Customer newCustomer = new Customer(4, "firstName", "lastName",  "email", "password");
		Customer actual = cs.addCustomer(newCustomer); 
		Customer expected = cd.getByID(4);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getCustomerByEmailCheck() {
		Customer newCustomer = new Customer(2, "firstName", "lastName",  "email", "password");
		Customer expected = newCustomer;
		Customer actual = cs.getCustomerByEmail(newCustomer.getEmail());
		assertEquals(expected,actual);
	}
	
	@Test
	public void loginCheck() throws LoginException {
		Customer newCustomer = new Customer(2, "firstName", "lastName",  "email", "password");
		Customer expected = newCustomer;
		Customer actual = cs.login("email", "password");
		assertEquals(expected, actual);
	}
	
}
