package com.pontointeligente.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.repository.EmpresaRepository;
import com.pontointeligente.services.EmpresaService;


@Service
public class EmpresaServiceImpl implements EmpresaService {
	
	private EmpresaRepository repository;

	@Override
	public Optional<Empresa> buscaPorCnpj(String cnpj) {
		return Optional.ofNullable(this.repository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persitir(Empresa empresa) {
		return this.repository.save(empresa);
	}

}
