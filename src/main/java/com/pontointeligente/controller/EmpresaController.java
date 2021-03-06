package com.pontointeligente.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.controller.base.BaseController;
import com.pontointeligente.controller.response.Response;
import com.pontointeligente.controller.utils.ControllerHelper;
import com.pontointeligente.domain.Empresa;
import com.pontointeligente.dto.EmpresaDto;
import com.pontointeligente.services.EmpresaService;

@RestController
@RequestMapping(ControllerHelper.API+"/empresas")
@CrossOrigin("*")
public class EmpresaController extends BaseController {

	
	@Autowired
	private EmpresaService empresaService;
	
	
	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscaEmpresaPorCnpj(@PathVariable("cnpj") String cnpj){
		
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		
		Optional<Empresa> empresa = this.empresaService.buscaPorCnpj(cnpj);
		
		if(!empresa.isPresent()) {
			response.getErrors().add("Empresa não encontrada :" + cnpj);
			return ResponseEntity.badRequest().body(response);
		}
		
		EmpresaDto dto = this.conversor.converterEmpresaParaEmpresaDTO(empresa.get());
		response.setData(dto);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	
	
	
	
}
