package com.pontointeligente.controller.validator;


import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.dto.CadastroPfDto;
import com.pontointeligente.dto.CadastroPjDto;
import com.pontointeligente.dto.LancamentoDto;
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
    
	
	public void validarDadosExistentesCadastroPJ(CadastroPjDto cadastroPJ, BindingResult result) {
		
		this.empresaService.buscaPorCnpj(cadastroPJ.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.funcionarioService.findByCpf(cadastroPJ.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.findByEmail(cadastroPJ.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
	
	}
	
	
    public void validarDadosExistentesCadastroPF(CadastroPfDto cadastroPF, BindingResult result) {
	         	
    	Optional<Empresa> empresa = this.empresaService.buscaPorCnpj(cadastroPF.getCnpj());
    	if(!empresa.isPresent()) {
    		result.addError(new ObjectError("empresa", "Empresa não cadastrada"));
    	}
	
		this.funcionarioService.findByCpf(cadastroPF.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.findByEmail(cadastroPF.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
	
	}
    
    
    public void validarLancamento(LancamentoDto lancamentoDto, BindingResult result) {
    	
    	if(lancamentoDto.getFuncionarioId() == null) {
    		result.addError(new ObjectError("funcionario", "Funcionario não informado"));
    		return;
    	}
    	
    	Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(lancamentoDto.getFuncionarioId());
    	if(!funcionario.isPresent()) {
    		result.addError(new ObjectError("funcionario", "Funcionario não encontrado"));
    	}
    	
    }
	
	
}
