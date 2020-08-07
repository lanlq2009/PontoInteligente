package com.pontointeligente.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pontointeligente.domain.Usuario;
import com.pontointeligente.enuns.PerfilEnum;


/**
 * É preciso agora criar uma classe ‘factory’ para converter nosso usuário no usuário
reconhecido pelo Spring Security
 * */
public class JwtUserFactory {
	
	/**
	* Converte e gera um JwtUser com base nos dados de um funcionário.
	*
	* @param funcionario
	* @return JwtUser
	*/
	public static JwtUser create ( Usuario usuario ) {
 		return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha (), mapToGrantedAuthorities (usuario.getPerfil()));
	}
	
	
	/**
	* Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	*
	* @param perfilEnum
	* @return List<GrantedAuthority>
	*/
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities ;
	}
	
	
	

}
