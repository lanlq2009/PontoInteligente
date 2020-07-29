package com.pontointeligente.controller;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.controller.base.BaseController;
import com.pontointeligente.controller.response.Response;
import com.pontointeligente.controller.utils.ControllerHelper;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.dto.FuncionarioDto;
import com.pontointeligente.services.FuncionarioService;


@RestController
@RequestMapping(ControllerHelper.API+"/funcionario")
@CrossOrigin("*")
public class FuncionarioController extends BaseController {
	
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Response<FuncionarioDto>> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioDto funcionarioDto,  BindingResult result) throws NoSuchAlgorithmException{
				
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(id);
		if(!funcionario.isPresent()) {
			result.addError(new ObjectError("funcionario","Funcionario não encontrado."));
			ResponseEntity.badRequest().body(result);
		}
		
		this.atualizarFuncionario(funcionario.get(), funcionarioDto, result);
		
		this.funcionarioService.persistir(funcionario.get());
		
		response.setData(conversor.converterFuncionarioParaFuncionarioDto(funcionario.get()));
		
		return ResponseEntity.ok(response);
		
	}
	
	
	
	private void atualizarFuncionario(Funcionario funcionario, FuncionarioDto funcionarioDto, BindingResult result) throws NoSuchAlgorithmException {
		
		  funcionario.setNome(funcionarioDto.getNome());
		  
		  if(funcionarioDto.getEmail()!= null) {
			  if(!funcionario.getEmail().equals(funcionarioDto.getEmail())) {
				  this.funcionarioService.findByEmail(funcionarioDto.getEmail()).ifPresent(func -> result.addError(new ObjectError("email","Email já existente")));
			      funcionario.setEmail(funcionarioDto.getEmail());
			  }
		  }
		  
		  funcionario.setQtdHorasAlmoco(null);
		  funcionarioDto.getQtdHorasAlmoco().ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		  
		  funcionario.setQtdHorasTrabalhoDia(null);
		  funcionarioDto.getQtdHorasTrabalhoDia().ifPresent(qtdHorasTrabDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabDia)));
		  
		  funcionario.setValorHora(null);
		  funcionarioDto.getValorHora().ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));
		  
		  //if(funcionarioDto.getSenha().isPresent()) {
			//funcionario.setSenha(PasswordUtils.getEncrypt(cadastroPJDto.getSenha()));
		  //}
		  		
	}
	

}
