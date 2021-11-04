package com.revature.drivers;

import java.util.List;

import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeePostgres;
import com.revature.models.Employee;

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
	}

}
