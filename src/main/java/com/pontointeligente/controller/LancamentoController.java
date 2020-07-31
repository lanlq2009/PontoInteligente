package com.pontointeligente.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.controller.base.BaseController;
import com.pontointeligente.controller.response.Response;
import com.pontointeligente.controller.utils.ControllerHelper;
import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.dto.LancamentoDto;
import com.pontointeligente.services.FuncionarioService;
import com.pontointeligente.services.LancamentoService;


@RestController
@RequestMapping(ControllerHelper.API+"/lancamento")
@CrossOrigin(origins="*")
public class LancamentoController extends BaseController {
	
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int totalDePaginas;
		
	@GetMapping(value="/funcionario/{funcionarioId}")
	public ResponseEntity<Response<Page<LancamentoDto>>> listarPorFuncionarioId(@PathVariable("funcionarioId") Long funcionarioId,
																				@RequestParam(value = "pag", defaultValue = "0") int pag,
																				@RequestParam(value = "ord", defaultValue = "id") String ord,
																				@RequestParam(value = "dir", defaultValue = "DESC") String dir){
		
		Response<Page<LancamentoDto>> response = new Response<Page<LancamentoDto>>();
		
		PageRequest pageRequest = PageRequest.of(pag, Integer.parseInt(ord),Sort.by(dir));
		
		Page<Lancamento> lancamentos = this.lancamentoService.findByFuncionarioId(funcionarioId, pageRequest);
		
		Page<LancamentoDto> lancamentoDtos = lancamentos.map(lancamento -> this.converterParaDto(lancamento));
		
		response.setData(lancamentoDtos);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	
	@GetMapping(value="/id/{id}")
	public ResponseEntity<Response<LancamentoDto>> listarPorId(@PathVariable("id") Long id){
		
		Response<LancamentoDto> response = new Response<LancamentoDto>();
		
		Optional<Lancamento> lancamentos = this.lancamentoService.buscarPorId(id);
		
		if(!lancamentos.isPresent()) {
			response.getErrors().add("Lancamento não encontrado "+ id);
			ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterParaDto(lancamentos.get()));
		
		return ResponseEntity.ok(response);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Response<LancamentoDto>> adicionar(@Valid @RequestBody LancamentoDto lancamentoDto,  BindingResult result) throws ParseException{
		
		
		Response<LancamentoDto> response = new Response<LancamentoDto>();
		
		this.settings();
		
		this.validator.validarLancamento(lancamentoDto, result);
		if(result.hasErrors()) {
		   result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
		   ResponseEntity.badRequest().body(response);
		}
		
		Lancamento lancamento = this.converterParaLancamento(lancamentoDto, result);
		
		lancamento = this.lancamentoService.persistir(lancamento);
		
		response.setData(this.converterParaDto(lancamento));
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@PutMapping(value="/id/{id}")
	public ResponseEntity<Response<LancamentoDto>> atualizar(@PathVariable("id") Long id,
			                                                 @Valid @RequestBody LancamentoDto lancamentoDto, 
			                                                 BindingResult result) throws ParseException {
		
		Response<LancamentoDto> response = new Response<LancamentoDto>();
		
		this.settings();
		
		this.validator.validarLancamento(lancamentoDto, result);
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			ResponseEntity.badRequest().body(response);
		}
		
        lancamentoDto.setId(Optional.of(id));
        
        Lancamento lancamento = this.converterParaLancamento(lancamentoDto, result);
		
		lancamento = this.lancamentoService.persistir(lancamento);
		
		response.setData(this.converterParaDto(lancamento));
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) throws ParseException {

		Response<String> response = new Response<String>();
		
		Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(id);
		
		if(!lancamento.isPresent()) {
			response.getErrors().add("Não existe lancamento");
			ResponseEntity.badRequest().body(response);
		}
		
		this.lancamentoService.remover(lancamento.get());
		
		return ResponseEntity.ok(response);
	
	}
	
	
	
	private LancamentoDto converterParaDto(Lancamento lancamento) {
		return super.conversor.converterLancamentoParaLancamentoDto(lancamento);
	}
	
	
	private Lancamento converterParaLancamento(LancamentoDto lancamentoDto, BindingResult result) throws ParseException {
		return super.conversor.converterDtoParaLancamento(lancamentoDto, lancamentoService, result);
	}
	
	
	
	private void settings() {
		super.settings(this.funcionarioService);
	}
	
	

}
