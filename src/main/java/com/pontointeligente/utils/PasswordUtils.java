
  package com.pontointeligente.utils;
  
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  
  public class PasswordUtils {
  
  
    public static String getEncrypt(String senha) { 
    	if (senha == null) {
			return senha;
		}
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
    }
  
  }
 