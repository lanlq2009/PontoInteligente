package com.pontointeligente.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.repository.LancamentoRepository;
import com.pontointeligente.services.LancamentoService;


@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public Optional<List<Lancamento>> findByFuncionarioId(Long funcionarioId) {
		return Optional.ofNullable(this.lancamentoRepository.findByFuncionarioId(funcionarioId));
	}

	@Override
	public Page<Lancamento> findByFuncionarioId(Long funcionarioId, Pageable pageable) {
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageable);
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Lancamento lancamento) {
	   this.lancamentoRepository.delete(lancamento);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		return this.lancamentoRepository.findById(id);
	}

	
	
	
	

	

}
