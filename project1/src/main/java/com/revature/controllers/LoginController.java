package com.revature.controllers;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.revature.models.User;
import com.revature.services.LoginServices;

import io.javalin.Javalin;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class LoginController {
	
	public static LoginServices ls = new LoginServices();

	public static void login(Context ctx){
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		String token = null;
		
		token = ls.loginCheck(username, password);
		if(token != null) {
			ctx.header("Authorization", token);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

}
