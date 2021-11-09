package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.exceptions.LoginException;
import com.revature.models.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeePostgres;
import com.revature.services.EmployeeService;

public class EmployeeServicesTest {

	private static EmployeeService es = new EmployeeService();
	private static EmployeeDao ed = new EmployeePostgres();
	
	
	@Test
	public void addCustomerCheck() throws EmailAlreadyExistsException {
		Employee newEmployee = new Employee(4, "firstName", "lastName",  "email", "password");
		Employee actual = es.addEmployee(newEmployee); 
		Employee expected = ed.getByID(4);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getEmployeeByEmailCheck() {
		Employee newEmployee = new Employee(2, "firstName", "lastName",  "email", "password");
		Employee expected = newEmployee;
		Employee actual = es.getEmployeeByEmail(newEmployee.getEmail());
		assertEquals(expected,actual);
	}
	
	@Test
	public void loginCheck() throws LoginException {
		Employee newEmployee = new Employee(2, "firstName", "lastName",  "email", "password");
		Employee expected = newEmployee;
		Employee actual = es.login("email", "password");
		assertEquals(expected, actual);
	}

}
