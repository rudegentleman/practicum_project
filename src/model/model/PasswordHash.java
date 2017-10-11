package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
	
	
// this dunction returns a hashed version of the password
	public static String getPasswordHash(String plainPass){
		
		String input = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(plainPass.getBytes(),0,plainPass.length());
			
			input = new BigInteger(1,digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return input;
		
		
	}
	

}
