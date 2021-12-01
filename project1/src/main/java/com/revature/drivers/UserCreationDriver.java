package com.revature.drivers;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;


public class UserCreationDriver {

	public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException { 
	    SecureRandom random = new SecureRandom();
	    byte[] salt = new byte[16];
	    random.nextBytes(salt);
	    
	    byte[] encoded = Base64.getEncoder().encode(salt);
	    

	    System.out.println("salt: " + new String(encoded));

	    String password = "helloworld";
	    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

	    byte[] hash = factory.generateSecret(spec).getEncoded();
	    encoded = Base64.getEncoder().encode(hash);

	    System.out.println("hash: " + new String(encoded));
	  } 

}
