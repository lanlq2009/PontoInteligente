package com.pontointeligente.controller.validator;


import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.pontointeligente.model.CadastroPJ;
import com.pontointeligente.services.EmpresaService;
import com.pontointeligente.services.FuncionarioService;

public class PontoInteligenteValidator {
	
    private EmpresaService empresaService;
	
	private FuncionarioService funcionarioService;

	public EmpresaService getEmpresaService() {
		return empresaService;
	}

	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
    
	
	public void validarDadosExistentes(CadastroPJ cadastroPJ, BindingResult result) {
		
		this.empresaService.buscaPorCnpj(cadastroPJ.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.funcionarioService.findByCpf(cadastroPJ.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.findByEmail(cadastroPJ.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
	
	}
	
	
}
