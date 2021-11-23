package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

public class LoginServices {
	
	UserDao ud = new UserPostgres();
	
	public User loginCheck(String username, String password){
		List<User> users = ud.getAll();
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
}
