package com.revature.services;

import java.util.List;

import com.revature.repositories.EmployeeDao;
// import com.revature.repositories.EmployeeList;
import com.revature.repositories.EmployeePostgres;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.models.Employee;

public class EmployeeService {
	private static EmployeeDao ed = new EmployeePostgres();
	
	public Employee addEmployee(Employee e) throws EmailAlreadyExistsException {
		Employee newEmployee = this.getEmployeeByEmail(e.getEmail());
		if(newEmployee != null) {
			throw new EmailAlreadyExistsException();
		}
		
		return ed.add(e);
	}
	
	public Employee getEmployeeByEmail(String email) {
		List<Employee> employees = ed.getAll();
		for(Employee e : employees) {
			if(e.getEmail().equals(email)) {
				return e;
			}
		}
		return null;
	}
	
	public Employee login(String email, String password) throws LoginException {
		Employee emp = this.getEmployeeByEmail(email);
		if(emp == null || !emp.getPassword().equals(password)) {
			throw new LoginException();
		}
		if(emp.getEmployeeID() == 987) {
			
		}
		return emp;
	}
}
