package com.revature.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementPostgres;


public class ReimbursementServices {
	
	ReimbursementDao rd = new ReimbursementPostgres();
	
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

}
