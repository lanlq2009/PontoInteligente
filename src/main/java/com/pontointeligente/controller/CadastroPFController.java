package com.pontointeligente.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.controller.base.ControllerBase;
import com.pontointeligente.controller.response.Response;
import com.pontointeligente.controller.utils.ControllerHelper;
import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.dto.CadastroPfDto;
import com.pontointeligente.services.EmpresaService;
import com.pontointeligente.services.FuncionarioService;

@RestController
@RequestMapping(ControllerHelper.API+"/cadastro/pf")
@CrossOrigin("*")
public class CadastroPFController extends ControllerBase {
	
	  @Autowired   
	  private EmpresaService empresaService;
	  
	  @Autowired
	  private FuncionarioService funcionarioService;
	  
	  @PostMapping
	  public ResponseEntity<Response<CadastroPfDto>> cadastrar(@Valid @RequestBody CadastroPfDto cadastroPF, BindingResult result)
			  throws NoSuchAlgorithmException {
		  		  
		  Response<CadastroPfDto> response = new Response<CadastroPfDto>();
		  
		  super.settings(empresaService, funcionarioService);
		  
		  this.validator.validarDadosExistentesCadastroPF(cadastroPF, result);
		  if(result.hasErrors()) {
			  result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			  return ResponseEntity.badRequest().body(response);
		  }
	
		  response.setData(this.cadastrarPF(cadastroPF));
		  
		  return ResponseEntity.ok(response);
		  
	  }
	  
	  
	  private CadastroPfDto cadastrarPF(CadastroPfDto cadastroPF) {
          
		  Funcionario funcionario = super.conversor.converterCadastroPfParaFuncionario(cadastroPF);
		  
		  Optional<Empresa> empresa = this.empresaService.buscaPorCnpj(cadastroPF.getCnpj());
		  empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		  
		  this.funcionarioService.persistir(funcionario);
		  
		  return this.conversor.converterFuncionarioParaCadastroPf(funcionario);
	
	  }
	  
	

}
