package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ski;
import com.revature.utilities.ConnectionUtil;

public class SkiPostgres implements SkiDao {

	private Ski makeNewSki(ResultSet rs) {

		try {
			int skiID = rs.getInt("s_id");
			String brand = rs.getString("s_brand");
			String model = rs.getString("s_model");
			Double price = rs.getDouble("s_price");
			int inStock = rs.getInt("s_in_stock");
			
			return new Ski(brand, model, price, inStock, skiID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Ski getByID(int id) {
		String sql = "select * from skis where s_id = ? ";
		Ski s = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return makeNewSki(rs);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public List<Ski> getAll() {
		String sql = "select * from skis;";
		List<Ski> skis = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				skis.add(makeNewSki(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return skis;
	}

	@Override
	public List<Ski> getInStock() {
		String sql = "select * from skis where s_in_stock > 0;";
		List<Ski> skis = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				skis.add(makeNewSki(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return skis;

	}

	@Override
	public Ski add(Ski skis) {
		String sql = "insert into skis (s_brand, s_model, s_price, s_in_stock) "
				+ "values (?, ?, ?, ?) returning s_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, skis.getBrand());
			ps.setString(2, skis.getModel());
			ps.setDouble(3, skis.getPrice());
			ps.setInt(4, skis.getInStock());

			ps.execute();
			ResultSet rs = ps.getResultSet();

			if(rs.next()) {
				skis.setSkiID(rs.getInt(1));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return skis;
	}

	public boolean update(Ski skis) {
		String sql = "update skis set s_brand = ?, s_model = ?, s_price = ?, s_in_stock = ? "
				+ "where s_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, skis.getBrand());
			ps.setString(2, skis.getModel());
			ps.setDouble(3, skis.getPrice());
			ps.setInt(4, skis.getInStock());
			ps.setInt(5, skis.getSkiID());

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

	public Ski remove(Ski skis) {
		String sql = "delete from skis where s_id = ?;";
		int rowsChanged = -1;
		int id = skis.getSkiID();
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
			return skis;
		}

	}

}
