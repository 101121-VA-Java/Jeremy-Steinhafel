package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

public class LoginServices {

	UserDao ud = new UserPostgres();

	public String loginCheck(String username, String password) {
		String token = null;
		User u = ud.getUserByUsername(username);
		if (u == null) {
			return null;
		}

		KeySpec spec = new PBEKeySpec(password.toCharArray(), u.getPasswordSalt(), 65536, 128);
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

			byte[] hash = factory.generateSecret(spec).getEncoded();

			if (Arrays.equals(u.getPasswordHash(), hash)) {
				token = u.getUserID() + ":" + u.getRoleID();
				return token;
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
