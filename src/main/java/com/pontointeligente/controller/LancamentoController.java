package com.pontointeligente.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.controller.base.BaseController;
import com.pontointeligente.controller.utils.ControllerHelper;
import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.dto.LancamentoDto;
import com.pontointeligente.services.FuncionarioService;
import com.pontointeligente.services.LancamentoService;


@RestController
@RequestMapping(ControllerHelper.API+"/lancamento")
@CrossOrigin(origins="*")
public class LancamentoController extends BaseController {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int totalDePaginas;
	
	
	
	
	
	
	
	
	
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
