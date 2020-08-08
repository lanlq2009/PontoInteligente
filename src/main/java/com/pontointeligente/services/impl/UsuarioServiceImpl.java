/*
 * package com.pontointeligente.services.impl;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.pontointeligente.domain.Usuario; import
 * com.pontointeligente.repository.UsuarioRepository; import
 * com.pontointeligente.services.UsuarioService;
 * 
 * 
 * @Service public class UsuarioServiceImpl implements UsuarioService {
 * 
 * @Autowired private UsuarioRepository usuarioRepository;
 * 
 * @Override public Optional<Usuario> buscarPorEmail(String email) { return
 * Optional.ofNullable(this.usuarioRepository.findByEmail(email)); }
 * 
 * }
 */