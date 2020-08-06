package com.pontointeligente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pontointeligente.domain.Usuario;


@Transactional(readOnly=true)
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
		
	Usuario findByEmail(String email);

}
