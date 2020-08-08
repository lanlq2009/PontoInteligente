package com.pontointeligente.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.security.JwtUserFactory;
import com.pontointeligente.services.FuncionarioService;


/**
 * Termos também que criar um serviço para manipular essa interface do UserDetails
 * */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Funcionario> funcionario = this.funcionarioService.findByEmail(username);
		if(funcionario.isPresent()) {
			return JwtUserFactory.create(funcionario.get());
		}
	
		throw new UsernameNotFoundException ("Email não encontrado.");
	
	}
	
	
	

}
