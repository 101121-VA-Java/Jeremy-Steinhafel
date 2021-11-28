package com.revature.drivers;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class Driver {

	private static Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		log.info("The main method has been called!");
		
		// Creating an instance of javalin and starting on port 8080
		Javalin app = Javalin.create((config) -> {
		config.enableCorsForAllOrigins();
		/*
		 * Enables CORS: Cross Origin Resource Sharing - protective mechanism built into
		 * most browsers - restricts resources to be only be allowed by webpages on the
		 * same domain
		*/
		config.defaultContentType = "application/json";
		});
		app.start();
		
		app.before(ctx -> {
		    ctx.header("Access-Control-Allow-Headers", "Authorization");
		    ctx.header("Access-Control-Expose-Headers", "Authorization");
		});
		app.routes(() -> {
			// login
			path("login", () ->{
				post(LoginController::login);
			});
			
			//requests path
			path("reimbursementRequests", () ->{
				// submitRequest
				path("submitRequest", () ->{
					post(ReimbursementController::submitRequest);
				});
				path("viewPending", () ->{
					get(ReimbursementController::viewPending);
				});
				path("viewResolved", () ->{
					get(ReimbursementController::viewResolved);
				});
			});
			
			//employees
			path("employee", () ->{
				get(UserController::getAllEmployees);
				// individual employee info
				path("{id}", () ->{
					get(UserController::getEmployeeByID);
					put(UserController::updateUserInfo);
				});
			});
		});
		
	}

}
