package com.pontointeligente.services;

import java.util.Optional;

import com.pontointeligente.domain.Funcionario;

public interface FuncionarioService {
	
	
    Optional<Funcionario> findByCpf(String cpf);
	
    Optional<Funcionario> findByEmail(String email);
		
	Optional<Funcionario> findByCpfOrEmail(String cpf, String email);
	
	Funcionario persistir(Funcionario funcionario);
	
	Optional<Funcionario> buscarPorId(Long id);

}
