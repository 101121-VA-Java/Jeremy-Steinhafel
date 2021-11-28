package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao extends GenericDao<Reimbursement>{
	List<Reimbursement> getByAuthorID(int id);
}
