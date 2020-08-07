
  package com.pontointeligente.security.utils;
  
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  
  public class PasswordUtils {
  
  
  public static String getEncrypt(String senha) { 
	  
	  BCryptPasswordEncoder criptos = new BCryptPasswordEncoder(); return criptos.encode(senha); 
	  
  }
  
  }
 