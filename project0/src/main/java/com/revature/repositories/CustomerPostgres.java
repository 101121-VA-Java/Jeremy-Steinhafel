package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.utilities.ConnectionUtil;

public class CustomerPostgres implements CustomerDao {

	@Override
	public Customer getByID(int id) {
		String sql = "select * from customers where c_id = ? ";
		Customer cust = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_first_name = rs.getString("c_first_name");
				String c_last_name = rs.getString("c_first_name");
				String c_email = rs.getString("c_username");
				String c_password = rs.getString("c_password");
				Boolean loggedIn = rs.getBoolean("c_logged_in");


				cust = new Customer(c_id, c_first_name, c_last_name, c_email, c_password, loggedIn);
			}
		} 
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return cust;
	}
	
	@Override
	public List<Customer> getAll(){
		String sql = "select * from customers;";
		List <Customer> customers = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int customerID = rs.getInt("c_id");
				String firstName = rs.getString("c_first_name");
				String lastName = rs.getString("c_last_name");
				String email = rs.getString("c_email");
				String password = rs.getString("c_password");
				Boolean loggedIn = rs.getBoolean("c_logged_in");
				
				Customer newCust = new Customer(customerID, firstName, lastName, email, password, loggedIn);
				customers.add(newCust);
			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	@Override
	public Customer add(Customer customer) {
		// int genID = -1;
		String sql = "insert into customers (c_id, c_first_name, c_last_name, c_email, c_password) "
					+ "values (?, ?, ?, ?, ?);";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, customer.getCustomerID());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getPassword());
			
			//ResultSet rs = ps.executeQuery();
			ps.executeUpdate();
			
//			if(rs.next()) {
//				genID = rs.getInt("e_id");
//			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public boolean update(Customer customer) {
		String sql = "update customers set c_first_name = ?, c_last_name = ?, c_email = ?, c_password = ?, c_logged_in = ? "
				+ "where c_id = ?;";
		
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPassword());
			ps.setBoolean(5, customer.isLoggedIn());
			ps.setInt(6, customer.getCustomerID());
			
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
	
	public Customer remove(Customer customer) {
		String sql = "delete from customers where e_id = ?;";
		int rowsChanged = -1;
		int id = customer.getCustomerID();
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
			return customer;
		}
		
	}

	
	
	
}
