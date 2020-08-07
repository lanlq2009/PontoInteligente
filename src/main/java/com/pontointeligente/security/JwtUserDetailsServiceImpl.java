package com.pontointeligente.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pontointeligente.domain.Usuario;
import com.pontointeligente.services.UsuarioService;


/**
 * Termos também que criar um serviço para manipular essa interface do UserDetails
 * */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(username);
		if(usuario.isPresent()) {
			return JwtUserFactory.create(usuario.get());
		}
	
		throw new UsernameNotFoundException ( "Email não encontrado." );
	
	}
	
	
	

}
