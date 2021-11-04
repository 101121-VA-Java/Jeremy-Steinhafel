package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.utilities.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao {

	@Override
	public Employee getByID(int id) {
		String sql = "select * from employees where e_id = ? ";
		Employee emp = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int e_id = rs.getInt("e_id");
				String e_first_name = rs.getString("e_first_name");
				String e_last_name = rs.getString("e_first_name");
				String e_email = rs.getString("e_username");
				String e_password = rs.getString("e_password");


				emp = new Employee(e_id, e_first_name, e_last_name, e_email, e_password);
			}
		} 
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	@Override
	public List<Employee> getAll(){
		String sql = "select * from employees;";
		List <Employee> employees = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int employeeID = rs.getInt("e_id");
				String firstName = rs.getString("e_first_name");
				String lastName = rs.getString("e_last_name");
				String email = rs.getString("e_email");
				String password = rs.getString("e_password");
				
				Employee newEmp = new Employee(employeeID, firstName, lastName, email, password);
				employees.add(newEmp);
			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	@Override
	public Employee add(Employee employee) {
		// int genID = -1;
		String sql = "insert into employees (e_id, e_first_name, e_last_name, e_email, e_password) "
					+ "values (?, ?, ?, ?, ?);";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, employee.getEmployeeID());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setString(3, employee.getEmail());
			ps.setString(3, employee.getPassword());
			
			//ResultSet rs = ps.executeQuery();
			ps.executeQuery();
			
//			if(rs.next()) {
//				genID = rs.getInt("e_id");
//			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	public boolean update(Employee employee) {
		String sql = "update employees set e_first_name = ?, e_last_name = ?, e_email = ?, e_password = ? "
				+ "where e_id = ?;";
		
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPassword());
			ps.setInt(5, employee.getEmployeeID());
			
			rowsChanged = ps.executeUpdate();
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rowsChanged > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Employee remove(Employee employee) {
		String sql = "delete from employees where e_id = ?;";
		int rowsChanged = -1;
		int id = employee.getEmployeeID();
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
		}
		catch (SQLException | IOException e){ 
			e.printStackTrace();
		}
		if(rowsChanged > 0) {
			return null;
		} else {
			return employee;
		}
		
	}

}
