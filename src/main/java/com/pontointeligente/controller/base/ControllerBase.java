package com.pontointeligente.controller.base;

import com.pontointeligente.controller.converter.Conversor;
import com.pontointeligente.controller.validator.PontoInteligenteValidator;
import com.pontointeligente.services.EmpresaService;
import com.pontointeligente.services.FuncionarioService;

public class ControllerBase {
	
    public PontoInteligenteValidator validator = new PontoInteligenteValidator();
	
	public Conversor conversor = new Conversor();
		
	public void settings(EmpresaService empresaService, FuncionarioService funcionarioService) {
	   this.validator.setEmpresaService(empresaService);
	   this.validator.setFuncionarioService(funcionarioService);
	}

}
