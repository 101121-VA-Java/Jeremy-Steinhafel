package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Skis;
import com.revature.utilities.ConnectionUtil;

public class SkiPostgres implements SkiDao {

	@Override
	public Skis getByID(int id) {
		String sql = "select * from skis where e_id = ? ";
		Skis s = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String brand = rs.getString("s_brand");
				String model = rs.getString("s_model");
				Double price = rs.getDouble("s_price");
				String offerStatus = rs.getString("s_offer_status");
				int inStock = rs.getInt("s_in_stock");
				int customerID = rs.getInt("s_customer_id");

				s = new Skis(brand, model, price, offerStatus, inStock, customerID);
			}
		} 
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	@Override
	public List<Skis> getAll(){
		String sql = "select * from skis;";
		List <Skis> skis = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				String brand = rs.getString("s_brand");
				String model = rs.getString("s_model");
				Double price = rs.getDouble("s_price");
				String offerStatus = rs.getString("s_offer_status");
				int inStock = rs.getInt("s_in_stock");
				int customerID = rs.getInt("s_customer_id");
				
				Skis newSkis = new Skis(brand, model, price, offerStatus, inStock, customerID);
				skis.add(newSkis);
			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return skis;
	}
	
	@Override
	public Skis add(Skis skis) {
		// int genID = -1;
		String sql = "insert into skis (s_brand, s_model, s_price, s_offer_status, s_in_stock, s_customer_id) "
					+ "values (?, ?, ?, ?, ?, ?);";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, skis.getBrand());
			ps.setString(2, skis.getModel());
			ps.setDouble(3, skis.getPrice());
			ps.setString(4, skis.getOfferStatus());
			ps.setInt(5, skis.getInStock());
			ps.setInt(6, skis.getCustomerID());
			
			//ResultSet rs = ps.executeQuery();
			 ps.executeUpdate();
			
//			if(rs.next()) {
//				genID = rs.getInt("e_id");
//			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return skis;
	}
	
	public boolean update(Skis skis) {
		String sql = "update skis set s_brand = ?, s_price = ?, s_offer_status = ?, s_in_stock = ? "
				+ "where s_customer_id = ? and s_model = ?;";
		
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, skis.getBrand());
			ps.setDouble(2, skis.getPrice());
			ps.setString(3, skis.getOfferStatus());
			ps.setInt(4, skis.getInStock());
			ps.setInt(5, skis.getCustomerID());
			ps.setString(6, skis.getModel());
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
	
	public Skis remove(Skis skis) {
		String sql = "delete from skis where s_customer_id = ? and s_model = ?;";
		int rowsChanged = -1;
		int id = skis.getCustomerID();
		String model = skis.getModel();
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, model);
			rowsChanged = ps.executeUpdate();
		}
		catch (SQLException | IOException e){ 
			e.printStackTrace();
		}
		if(rowsChanged > 0) {
			return null;
		} else {
			return skis;
		}
		
	}

}
