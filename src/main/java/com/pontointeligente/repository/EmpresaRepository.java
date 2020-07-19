package com.pontointeligente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pontointeligente.domain.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	@Transactional(readOnly=true)
	@Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
	Empresa findByCnpj(@Param("cnpj") String cnpj);

}
