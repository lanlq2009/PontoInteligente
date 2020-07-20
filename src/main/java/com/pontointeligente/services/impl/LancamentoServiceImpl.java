package com.pontointeligente.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.repository.LancamentoRepository;
import com.pontointeligente.services.LancamentoService;

public class LancamentoServiceImpl implements LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public Optional<List<Lancamento>> findByFuncionarioId(Long funcionarioId) {
		return Optional.ofNullable(this.lancamentoRepository.findByFuncionarioId(funcionarioId));
	}

	@Override
	public Optional<Page<Lancamento>> findByFuncionarioId(Long funcionarioId, Pageable pageable) {
		return Optional.ofNullable(this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageable));
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		return this.lancamentoRepository.save(lancamento);
	}
	
	

	

}
