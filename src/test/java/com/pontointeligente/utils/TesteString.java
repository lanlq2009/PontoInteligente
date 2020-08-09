package com.pontointeligente.utils;

public class TesteString {

	public static void main(String[] args) {
		
		String pass = "123456";
		
		String encriptada = PasswordUtils.getEncrypt(pass);
		
		System.out.println("senha :: " + encriptada);
		
		// TODO Auto-generated method stub

	}

}
