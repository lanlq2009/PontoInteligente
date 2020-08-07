package com.pontointeligente.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * O Spring Security depende de um usuário que implemente a interface UserDetails, pois é
através desses dados que ele controlará quem está autenticado no sistema
 * */
public class JwtUser implements UserDetails {
	
	private Long id ;
	private String username ;
	private String password ;
	private Collection <? extends GrantedAuthority > authorities ;
	
	public JwtUser(Long id, String username , String password,Collection <? extends GrantedAuthority > authorities ) {
		this . id = id ;
		this . username = username ;
		this . password = password ;
		this . authorities = authorities ;
	}
	
	public Long getId () {
		return id ;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
