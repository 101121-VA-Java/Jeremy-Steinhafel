package com.revature.controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementServices;

import org.apache.commons.mail.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.javalin.Javalin;
import static j2html.TagCreator.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReimbursementController {
	
	public static ReimbursementServices rs = new ReimbursementServices();
	private static Logger log = LogManager.getRootLogger();

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
	
	public static void viewAllPending(Context ctx) {
		String token = ctx.header("Authorization");
		if(token == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		List<Reimbursement> pending = rs.getAllPendingRequests();
		ctx.json(pending);
		ctx.status(HttpCode.OK);
	}
	
	public static void approveRequests(Context ctx) {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("js.reimbursement.app@gmail.com", "Reimbursements1!"));
        email.setSSLOnConnect(true);
        try {
			email.setFrom("js.reimbursement.app@gmail.com");
			email.setSubject("Reimbursement Request Approved");
			email.setMsg("Your recent request for reimbursement has been approved!");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Approve requests email error!");
		}
		
		String token = ctx.header("Authorization");
		if(token == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		System.out.println(token);
		String[] info = token.split(":");
		int token_id = Integer.parseInt(info[0]);
		String[] approvalArray = ctx.body().split(",");
		for(int i = 0; i < approvalArray.length; i++) {
			int approveID = Integer.parseInt(approvalArray[i]);
			rs.approveByReimbID(approveID, token_id);
			User u = rs.getUserByReimbID(approveID);
			try {
				email.addTo(u.getEmail());
				email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Accept email login error!");
			}
		}
	}
	
	public static void denyRequests(Context ctx) {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("js.reimbursement.app@gmail.com", "Reimbursements1!"));
        email.setSSLOnConnect(true);
        try {
			email.setFrom("rockpilestudios@gmail.com");
			email.setSubject("Reimbursement Request Denied");
			email.setMsg("Your recent request for reimbursement has been denied.");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Deny email login error!");
		}
        
        
		String token = ctx.header("Authorization");
		if(token == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		String[] info = token.split(":");
		int token_id = Integer.parseInt(info[0]);
		String[] denialArray = ctx.body().split(",");
		for(int i = 0; i < denialArray.length; i++) {
			int denialID = Integer.parseInt(denialArray[i]);
			rs.denyByReimbID(denialID, token_id);
			User u = rs.getUserByReimbID(denialID);
			try {
				email.addTo(u.getEmail());
				email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Deny email login error!");
			}
		}
		
	}
	
	public static void viewHistory(Context ctx) {
		String token = ctx.header("Authorization");
		if(token == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		List<Reimbursement> resolved = rs.getAllResolvedRequests();
		ctx.json(resolved);
		ctx.status(HttpCode.OK);
	}
	
	public static void viewAllRequestsByUser(Context ctx) {
		String token = ctx.header("Authorization");
		if(token == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		int id = Integer.parseInt(ctx.pathParam("id"));
				
		List<Reimbursement> requestsByUser = rs.getAllByAuthorID(id);
		if(requestsByUser == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		}
		ctx.json(requestsByUser);
		ctx.status(HttpCode.OK);
		
	}
	
}
