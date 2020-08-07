package com.pontointeligente.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * Existe mais um último recurso que é a autorização por perfil, conforme definido no enum
anteriormente, assim é possível validar cada ação de um controle.

Para realizar tal validação, usamos a anotação ‘@PreAuthorize’, e no exemplo a seguir é
validado se o usuário possui o perfil de administrador para acessar a ação do controller:
 *
 * */

@RestController
@RequestMapping ("/api/exemplo")
public class ExemploController {
	
	
	@GetMapping(value="/{nome}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String exemplo(@PathVariable ("nome") String nome) {
		return "Olá " + nome ;
	}
	

}
