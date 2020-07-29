package com.pontointeligente.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.controller.base.BaseController;
import com.pontointeligente.controller.response.Response;
import com.pontointeligente.controller.utils.ControllerHelper;
import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.dto.CadastroPjDto;
import com.pontointeligente.services.EmpresaService;
import com.pontointeligente.services.FuncionarioService;

@RestController
@RequestMapping(ControllerHelper.API)
@CrossOrigin(origins="*")
public class CadastroPJController extends BaseController {
	
	
	@Autowired
	private EmpresaService empresaService;
	
	
	@Autowired
	private FuncionarioService funcionarioService;
	
			
	@PostMapping("cadastro/pj") 
	public ResponseEntity<Response<CadastroPjDto>> cadastrar(@Valid @RequestBody CadastroPjDto cadastroPj, BindingResult result)
	  throws NoSuchAlgorithmException {
	  
		  Response<CadastroPjDto> response = new Response<CadastroPjDto>();
		  
		  this.settings();
		  
		  this.validator.validarDadosExistentesCadastroPJ(cadastroPj, result);
		  
		  if(result.hasErrors()) { 
			  result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage())); 
			  return ResponseEntity.badRequest().body(response); 
		  }
		  
		  Empresa empresa = this.conversor.converterEmpresa(cadastroPj);
		  
		  this.empresaService.persitir(empresa);
		  
		  Funcionario funcionario = this.conversor.converterDtoParaFuncionario(cadastroPj, true);
		  
		  funcionario.setEmpresa(empresa);
		  
		  this.funcionarioService.persistir(funcionario);
		  
		  response.setData(this.conversor.converterCadastroPJ(funcionario));
		  
		  return ResponseEntity.ok(response);
	  
	}
	
	private void settings() {
		this.validator.setEmpresaService(this.empresaService);
		this.validator.setFuncionarioService(this.funcionarioService);
	}
	
	
	

}
