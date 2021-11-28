package com.revature.controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementServices;

public class ReimbursementController {
	
	public static ReimbursementServices rs = new ReimbursementServices();

	public static void submitRequest(Context ctx) {
		String amountString = ctx.formParam("amount");
		String reimbursementTypeString = ctx.formParam("reimbursementType");
		
		
		double amount = Double.parseDouble(amountString);
		int reimbursementType = Integer.parseInt(reimbursementTypeString);
		String description = ctx.formParam("description");
		
		String token = ctx.header("Authorization");
		System.out.println(token);
		String[] info = token.split(":");
		int token_id = Integer.parseInt(info[0]);
		
		Reimbursement r = rs.createNewReimbursement(token_id, amount, reimbursementType, description);
		
		if (r != null) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		
	}
	
	public static void viewPending(Context ctx) {
		String token = ctx.header("Authorization");
		String[] info = token.split(":");
		int token_id = Integer.parseInt(info[0]);
		List<Reimbursement> pending = rs.getPendingByAuthorID(token_id);
		ctx.json(pending);
		ctx.status(HttpCode.OK);
	}
	
	public static void viewResolved(Context ctx) {
		String token = ctx.header("Authorization");
		String[] info = token.split(":");
		int token_id = Integer.parseInt(info[0]);
		List<Reimbursement> resolved = rs.getResolvedByAuthorID(token_id);
		ctx.json(resolved);
		ctx.status(HttpCode.OK);
	}
	
}
