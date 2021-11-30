package com.revature.controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementServices;
import com.revature.services.UserServices;

public class UserController {

	public static UserServices us = new UserServices();
	
	public static void getEmployeeByID(Context ctx) {
		String token = ctx.header("Authorization");
		int id = Integer.parseInt(ctx.pathParam("id"));

		User employee = us.getEmployeeByID(id);

		if (employee != null) {
			ctx.json(employee);
			ctx.status(HttpCode.OK);
		} else {
//			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
	}
	
	public static void updateUserInfo(Context ctx) {
		String token = ctx.header("Authorization");
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		User u = ctx.bodyAsClass(User.class);
		User uCompare = us.getEmployeeByID(id);
		
		if(u.getUsername() != null) {
			uCompare.setUsername(u.getUsername());
		}
		
		if(u.getFirstName() != null) {
			uCompare.setFirstName(u.getFirstName());
		}
		
		if(u.getLastName() != null) {
			uCompare.setLastName(u.getLastName());
		}
		
		if(u.getEmail() != null) {
			uCompare.setEmail(u.getEmail());
		}
		
		if(us.updateUserByID(uCompare)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
	}

	public static void getAllUsers(Context ctx) {
		List<User> allUsers = us.getAllUsers();
		if (allUsers != null) {
			ctx.json(allUsers);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}
	
}
