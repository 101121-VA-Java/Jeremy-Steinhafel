package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

public class LoginServices {
	
	UserDao ud = new UserPostgres();
	
	public String loginCheck(String username, String password){
		String token = null;
		List<User> users = ud.getAll();
		for(User u : users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				token = u.getUserID() + ":" + u.getRoleID();
				return token;
			}
		}
		return null;
	}
}
