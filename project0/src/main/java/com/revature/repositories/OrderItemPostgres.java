package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Order;
import com.revature.models.OrderItem;
import com.revature.utilities.ConnectionUtil;

public class OrderItemPostgres implements OrderItemDao{

	private OrderItem makeNewOrderItem(ResultSet rs) {
		try {
			int orderItemID = rs.getInt("oi_id");
			int orderID= rs.getInt("oi_order_id");
			int skiID = rs.getInt("oi_ski_id");
			int orderQuantity = rs.getInt("oi_quantity");
			String orderItemStatus = rs.getString("oi_status");
			
			return new OrderItem(orderItemID, orderID, skiID, orderQuantity);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public OrderItem getByID(int id) {
		String sql = "select * from order_items where oi_id = ? ;";
		OrderItem oi = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return makeNewOrderItem(rs);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return oi;
	}
	
	@Override
	public List<OrderItem> getByOrderID(int orderID){
		String sql = "select * from order_items where oi_order_id = ? ;";
		List<OrderItem> orderItem = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				orderItem.add(makeNewOrderItem(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public List<OrderItem> getAll() {
		String sql = "select * from order_items;";
		List<OrderItem> orderItem = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				orderItem.add(makeNewOrderItem(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public OrderItem add(OrderItem orderItem) {
		String sql = "insert into order_items (oi_order_id, oi_ski_id, oi_quantity, oi_status) "
				+ "values (?, ?, ?, ?) returning oi_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, orderItem.getOrderID());
			ps.setInt(2, orderItem.getSkiID());
			ps.setInt(3, orderItem.getOrderQuantity());
			ps.setString(4, orderItem.getOrderItemStatus());

			ps.execute();
			ResultSet rs = ps.getResultSet();;

			if(rs.next()) {
				orderItem.setOrderItemID(rs.getInt(1));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

	public boolean update(OrderItem orderItem) {
		String sql = "update order_items set oi_order_id = ?, oi_ski_id = ?, oi_quantity = ?, oi_status = ? "
				+ "where oi_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, orderItem.getOrderID());
			ps.setInt(2, orderItem.getSkiID());
			ps.setInt(3, orderItem.getOrderQuantity());
			ps.setString(4, orderItem.getOrderItemStatus());
			ps.setInt(5, orderItem.getOrderItemID());

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

	public OrderItem remove(OrderItem orderItem) {
		String sql = "delete from order_items where oi_id = ?;";
		int rowsChanged = -1;
		int id = orderItem.getOrderItemID();
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
			return orderItem;
		}

	}

}
