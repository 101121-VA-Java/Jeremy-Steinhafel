package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgres implements UserDao {
	
	private User makeNewUser(ResultSet rs) {
		
		try {
			int userID = rs.getInt("ers_user_id");
			String username = rs.getString("ers_username");
			byte[] passwordHash = rs.getBytes("ers_password_hash");
			byte[] passwordSalt = rs.getBytes("ers_password_salt");
			String firstName = rs.getString("user_first_name");
			String lastName = rs.getString("user_last_name");
			String email = rs.getString("user_email");
			int roleID = rs.getInt("user_role_id");
			
			return new User(userID, username, passwordHash, passwordSalt, firstName, lastName, email, roleID);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
				
	}

	@Override
	public User add(User u) {
		String sql = "insert into ers_users (ers_user_id, ers_username, ers_password_hash, ers_password_salt, user_first_name, user_last_name, user_email, user_role_id) "
				+ "values (?, ?, ?, ?, ?, ?, ?);";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, u.getUserID());
			ps.setString(2, u.getUsername());
			ps.setBytes(3, u.getPasswordHash());
			ps.setBytes(4, u.getPasswordSalt());
			ps.setString(5, u.getFirstName());
			ps.setString(6, u.getLastName());
			ps.setString(7, u.getEmail());
			ps.setInt(8, u.getRoleID());
			

			ps.execute();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User getByID(int id) {
		String sql = "select * from ers_users where ers_user_id = ?;";
		User u = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return makeNewUser(rs);
							}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<User> getAll() {
		String sql = "select * from ers_users;";
		List<User> u = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				u.add(makeNewUser(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean update(User u) {
		String sql = "update ers_users set ers_username = ?, ers_password = ?, user_first_name = ?, user_last_name = ?, user_email = ?, user_role_id = ? "
				+ "where ers_user_id = ?;" ;
		
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, u.getUsername());
			ps.setBytes(2, u.getPasswordHash());
			ps.setBytes(3, u.getPasswordSalt());
			ps.setString(4, u.getFirstName());
			ps.setString(5, u.getLastName());
			ps.setString(6, u.getEmail());
			ps.setInt(7, u.getRoleID());
			ps.setInt(8, u.getUserID());

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

	@Override
	public User remove(User u) {
		String sql = "delete from ers_users where ers_user_id = ?;";
		int rowsChanged = -1;
		int id = u.getUserID();
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
			return u;
		}
	}
	
	@Override
	public User getUserByUsername(String username) {
		String sql = "select * from ers_users where ers_username = ?;";
		User u = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return makeNewUser(rs);
							}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	

}
