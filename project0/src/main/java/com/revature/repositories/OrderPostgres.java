package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Order;
import com.revature.models.Ski;
import com.revature.utilities.ConnectionUtil;

public class OrderPostgres implements OrderDao {

	private Order makeNewOrder(ResultSet rs) {
		try {
			int orderID = rs.getInt("o_id");
			String orderStatus = rs.getString("o_status");
			int customerID = rs.getInt("o_customer_id");
			LocalDate purchaseDate = (LocalDate) rs.getObject("o_purchase_date", LocalDate.class);
			
			return new Order(orderID, orderStatus, customerID, purchaseDate);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Order getByID(int id) {
		String sql = "select * from orders where o_id = ? ;";
		Order o = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return makeNewOrder(rs);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public List<Order> getAll() {
		String sql = "select * from orders;";
		List<Order> orders = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				orders.add(makeNewOrder(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public Order add(Order order) {
		String sql = "insert into orders (o_status, o_customer_id, o_purchase_date) "
				+ "values (?, ?, ?) returning o_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, order.getOrderStatus());
			ps.setInt(2, order.getCustomerID());
			ps.setObject(3, order.getPurchaseDate());

			ps.execute();
			ResultSet rs = ps.getResultSet();
			

			if(rs.next()) {
				order.setOrderID(rs.getInt(1));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return order;
	}

	public boolean update(Order order) {
		String sql = "update orders set o_status = ?, o_customer_id = ?, o_purchase_date = ? "
				+ "where o_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, order.getOrderStatus());
			ps.setInt(2, order.getCustomerID());
			ps.setObject(3, order.getPurchaseDate());
			ps.setInt(4, order.getOrderID());

			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rowsChanged > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Order remove(Order order) {
		String sql = "delete from orders where o_id = ?;";
		int rowsChanged = -1;
		int id = order.getOrderID();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rowsChanged > 0) {
			return null;
		} else {
			return order;
		}

	}

	@Override
	public Order getByStatus(int customerID, String status) {
		String sql = "select * from orders where o_status = ? and o_customer_id = ?;";
		Order o = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, customerID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return makeNewOrder(rs);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return o;
	}
	
}
