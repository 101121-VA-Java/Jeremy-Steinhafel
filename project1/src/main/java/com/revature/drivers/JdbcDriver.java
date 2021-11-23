package com.revature.drivers;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;


public class JdbcDriver {

	public static void main(String[] args) {
//		EmployeeDao ed = new EmployeePostgres();
//		List<Employee> emps = ed.getAll();
//		for(Employee e: emps) {
//			System.out.println(e.getEmployeeID());
//			System.out.println(e.getFirstName());
//			System.out.println(e.getLastName());
//			System.out.println(e.getEmail());
//			System.out.println(e.getPassword());
//		}
		
		UserDao ud = new UserPostgres();
		List<User> users = ud.getAll();
		for(User u: users) {
			System.out.println(u.getUserID());
			System.out.println(u.getUsername());
			System.out.println(u.getFirstName());
			System.out.println(u.getLastName());
			System.out.println(u.getEmail());
			System.out.println(u.getRoleID());
		}

	}

}
