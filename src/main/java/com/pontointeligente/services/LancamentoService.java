package com.pontointeligente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.pontointeligente.domain.Lancamento;

public interface LancamentoService {
	
	
    Optional<List<Lancamento>> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);
	
    Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable pageable);
	
	Lancamento persistir(Lancamento lancamento);

	void remover(Lancamento lancamento);
	
	Optional<Lancamento> buscarPorId(Long id);
	
}
