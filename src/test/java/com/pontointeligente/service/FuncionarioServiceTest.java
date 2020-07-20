package com.pontointeligente.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.repository.FuncionarioRepository;
import com.pontointeligente.services.FuncionarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") 
public class FuncionarioServiceTest {
	
	
	@MockBean
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@Before
	public void setUp(){
		BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		//BDDMockito.given(this.funcionarioRepository.findById(Mockito.anyLong())).willReturn(Optional<Funcionario> f);
	}
	
	
	@Test
	public void findPorCpfTest() {
		Optional<Funcionario> f = this.funcionarioService.findByCpf("24291173474");
		assertTrue(f.isPresent());	
	}
	
	
	@Test
	public void persistirTest() {
		Funcionario f = this.funcionarioService.persistir(new Funcionario());
		assertNotNull(f);
	}
	
	@Test
	public void findPorEmailTest() {
		Optional<Funcionario> f = this.funcionarioService.findByEmail("atyle@uss.un");
		assertTrue(f.isPresent());
	}
	
	
   	

}
