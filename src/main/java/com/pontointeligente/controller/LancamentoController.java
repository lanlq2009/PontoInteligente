package com.pontointeligente.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import net.sf.ehcache.search.Direction;


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
