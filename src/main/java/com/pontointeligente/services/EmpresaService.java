package com.pontointeligente.services;

import java.util.Optional;

import com.pontointeligente.domain.Empresa;

public interface EmpresaService {
	
	Optional<Empresa> buscaPorCnpj(String cnpj);
	
	Empresa persitir(Empresa empresa);
	

}
