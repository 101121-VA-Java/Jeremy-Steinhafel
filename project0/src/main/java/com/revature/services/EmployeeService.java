package com.revature.services;

import java.util.List;

import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeList;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.models.Employee;

public class EmployeeService {
	private static EmployeeDao ed = new EmployeeList();
	
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

}
