package com.pontointeligente.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.repository.FuncionarioRepository;
import com.pontointeligente.services.FuncionarioService;


@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository respository;
	
	@Override
	public Optional<Funcionario> findByCpf(String cpf) {
		return Optional.ofNullable(this.respository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> findByEmail(String email) {
		return Optional.ofNullable(this.respository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> findByCpfOrEmail(String cpf, String email) {
		return Optional.ofNullable(this.respository.findByCpfOrEmail(cpf, email));
	}

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		return this.respository.save(funcionario);
	}

}
