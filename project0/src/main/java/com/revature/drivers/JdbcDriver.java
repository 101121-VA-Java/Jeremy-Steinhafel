package com.revature.drivers;

import java.util.List;

import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerPostgres;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeePostgres;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.SkiPostgres;
import com.revature.repositories.SkiDao;
import com.revature.models.Skis;

public class JdbcDriver {

	public static void main(String[] args) {
		EmployeeDao ed = new EmployeePostgres();
		List<Employee> emps = ed.getAll();
		for(Employee e: emps) {
			System.out.println(e.getEmployeeID());
			System.out.println(e.getFirstName());
			System.out.println(e.getLastName());
			System.out.println(e.getEmail());
			System.out.println(e.getPassword());
		}
		
		CustomerDao cd = new CustomerPostgres();
		List<Customer> custs = cd.getAll();
		for(Customer c: custs) {
			System.out.println(c.getCustomerID());
			System.out.println(c.getFirstName());
			System.out.println(c.getLastName());
			System.out.println(c.getEmail());
			System.out.println(c.getPassword());
		}
		
		SkiDao sd = new SkiPostgres();
		
		Skis newSkis = new Skis("Nordica", "Enforcer188", 900.00, "Available", 3);
		sd.remove(newSkis);
		
		List<Skis> skis = sd.getAll();
		for(Skis s: skis) {
			System.out.println(s.getBrand());
			System.out.println(s.getModel());
			System.out.println(s.getPrice());
			System.out.println(s.getOfferStatus());
			System.out.println(s.getInStock());
			System.out.println(s.getCustomerID());
		}
	}

}
