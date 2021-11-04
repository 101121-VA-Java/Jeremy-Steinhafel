package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeList implements EmployeeDao {

	private List<Employee> employees;
	
	public EmployeeList() {
		employees = new ArrayList<>();
		
	}
	
	@Override
	public Employee add(Employee t) {
		t.setEmployeeID(employees.size());
		employees.add(t);
		return t;
	}
	
	@Override
	public Employee getByID(int id) {
		for(Employee e: employees) {
			if(e.getEmployeeID() == id) {
				return e;
			}	
		}
		return null;
	}
	
	@Override
	public List<Employee> getAll(){
		return employees;
	}
	
	@Override
	public boolean update(Employee t) {
		Employee temp = getByID(t.getEmployeeID());
		if(temp == null || temp.equals(t)) {
			return false;
		}
		employees.set(t.getEmployeeID(), t);
		return true;
	}
	
	@Override
	public Employee remove(Employee t) {
		employees.remove(t);
		return t;
	}

}
