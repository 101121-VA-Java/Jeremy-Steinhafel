package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementPostgres implements ReimbursementDao {
	
	private Reimbursement makeNewReimbursement(ResultSet rs) {
		
		try {
			int reimbID = rs.getInt("reimb_id");
			double amount = rs.getDouble("reimb_amount");
			Timestamp submitted = (Timestamp) rs.getObject("reimb_submitted");
			Timestamp resolved = (Timestamp) rs.getObject("reimb_resolved");
			String description = rs.getString("reimb_description");
			// bytea
			int author = rs.getInt("reimb_author");
			int resolver = rs.getInt("reimb_resolver");
			int statusID = rs.getInt("status_id");
			int typeID = rs.getInt("type_id");
			
			return new Reimbursement(reimbID, amount, submitted, resolved, description, author, resolver, statusID, typeID );
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
				
	}

	@Override
	public Reimbursement add(Reimbursement r) {
		String sql = "insert into ers_reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id ) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, r.getReimbursementID());
			ps.setDouble(2, r.getAmount());
			ps.setObject(3, r.getSubmitted());
			ps.setObject(4, r.getResolved());
			ps.setString(5, r.getDescription());
			ps.setInt(6, r.getAuthor());
			ps.setInt(7, r.getResolver());
			ps.setInt(8, r.getStatusID());
			ps.setInt(9, r.getTypeID());

			ps.execute();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursement getByID(int id) {
		String sql = "select * from ers_reimbursement where reimb_id = ?;";
		Reimbursement r = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return makeNewReimbursement(rs);
							}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Reimbursement> getAll() {
		String sql = "select * from ers_reimbursement;";
		List<Reimbursement> r = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				r.add(makeNewReimbursement(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean update(Reimbursement r) {
		String sql = "update ers_reimbursement set reimb_amount = ?, reimb_submitted = ?, reimb_resolved = ?, reimb_description = ?, reimb_author = ?, reimb_resolver = ?, reimb_status_id = ?, reimb_type_id = ? "
				+ "where reimb_id = ?;" ;
		
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setDouble(1, r.getAmount());
			ps.setObject(2, r.getSubmitted());
			ps.setObject(3, r.getResolved());
			ps.setString(4, r.getDescription());
			ps.setInt(5, r.getAuthor());
			ps.setInt(6, r.getResolver());
			ps.setInt(7, r.getStatusID());
			ps.setInt(8, r.getTypeID());
			ps.setInt(9, r.getReimbursementID());

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
	public Reimbursement remove(Reimbursement r) {
		String sql = "delete from ers_reimbursement where reimb_id = ?;";
		int rowsChanged = -1;
		int id = r.getReimbursementID();
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
			return r;
		}
	}


}
