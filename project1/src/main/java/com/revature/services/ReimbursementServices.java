package com.revature.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementPostgres;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;


public class ReimbursementServices {
	
	ReimbursementDao rd = new ReimbursementPostgres();
	UserDao ud = new UserPostgres();
	
	public Reimbursement createNewReimbursement(int token_id, double amount, int reimbursementType, String description){
		
		Reimbursement r = new Reimbursement();
		
		r.setAmount(amount);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		r.setSubmitted(timestamp);
		
		r.setDescription(description);
				
		r.setAuthor(token_id);
		
		r.setStatusID(1);
		r.setTypeID(reimbursementType);
		
		rd.add(r);
		
		return r;
	}
	
	public List<Reimbursement> getPendingByAuthorID(int token_id){
		List<Reimbursement> reimbursements = rd.getByAuthorID(token_id);
		List<Reimbursement> pendingReimbursements = new ArrayList<>();
		for(Reimbursement r: reimbursements) {
			if(r.getStatusID() == ReimbursementStatus.PENDING) {
				pendingReimbursements.add(r);
			}
		}
		return pendingReimbursements;
	}
	
	public List<Reimbursement> getResolvedByAuthorID(int token_id){
		List<Reimbursement> reimbursements = rd.getByAuthorID(token_id);
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		for(Reimbursement r: reimbursements) {
			if(r.getStatusID() == ReimbursementStatus.APPROVED || r.getStatusID() == ReimbursementStatus.DENIED) {
				resolvedReimbursements.add(r);
			}
		}
		return resolvedReimbursements;
	}
	
	public List<Reimbursement> getAllPendingRequests(){
		List<Reimbursement> allReimbursements = rd.getAll();
		List<Reimbursement> allPendingReimbursements = new ArrayList<>();
		for(Reimbursement r: allReimbursements) {
			if(r.getStatusID() == ReimbursementStatus.PENDING) {
				allPendingReimbursements.add(r);
			}
		}
		return allPendingReimbursements;
	}
	
	public List<Reimbursement> getAllResolvedRequests(){
		List<Reimbursement> allReimbursements = rd.getAll();
		List<Reimbursement> allResolvedReimbursements = new ArrayList<>();
		for(Reimbursement r: allReimbursements) {
			if(r.getStatusID() == ReimbursementStatus.APPROVED || r.getStatusID() == ReimbursementStatus.DENIED ) {
				allResolvedReimbursements.add(r);
			}
		}
		return allResolvedReimbursements;
	}
	
	public boolean approveByReimbID(int ReimbID, int resolverID) {
		Reimbursement r = rd.getByID(ReimbID);
		r.setStatusID(ReimbursementStatus.APPROVED);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		r.setResolved(timestamp);
		
		r.setResolver(resolverID);
		
		return rd.update(r);
	}
	
	public boolean denyByReimbID(int ReimbID, int resolverID) {
		Reimbursement r = rd.getByID(ReimbID);
		r.setStatusID(ReimbursementStatus.DENIED);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		r.setResolved(timestamp);
		
		r.setResolver(resolverID);
		
		return rd.update(r);
	}
	
	public List<Reimbursement> getAllByAuthorID(int id){
		List<Reimbursement> reimbursements = rd.getByAuthorID(id);
		
		return reimbursements;
	}
	
	public User getUserByReimbID(int ReimbID) {
		Reimbursement r = rd.getByID(ReimbID);
		User u = ud.getByID(r.getAuthor());
		return u;
	}
	
	
}
