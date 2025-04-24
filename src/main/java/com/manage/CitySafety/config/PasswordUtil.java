package com.manage.CitySafety.config;

import java.util.Base64;

public class PasswordUtil {
	
	public static String encode(String plainPassword) {
        return Base64.getEncoder().encodeToString(plainPassword.getBytes());
    }

    // Decode Base64 to plain password
    public static String decode(String encodedPassword) {
        return new String(Base64.getDecoder().decode(encodedPassword));
    }

}
