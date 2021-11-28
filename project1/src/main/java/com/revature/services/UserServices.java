package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

public class UserServices {

	UserDao ud = new UserPostgres();
	
	public User getEmployeeByID(int id) {
		User u = ud.getByID(id);
		return u;
	}
	
	public boolean updateUserByID(User u) {

		return ud.update(u);
	}

}
