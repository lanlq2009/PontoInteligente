package com.pontointeligente.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.repository.LancamentoRepository;
import com.pontointeligente.services.LancamentoService;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") 
public class LancamentoServiceTest {
	
	
	@MockBean
	private LancamentoRepository repository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	
	@Before
	public void setUp() {
		BDDMockito.given(this.repository.findByFuncionarioId(Mockito.anyLong())).willReturn(new ArrayList<Lancamento>());
		BDDMockito.given(this.repository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class))).
		                 willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.repository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
	}
	
	
	@Test 
	public void devePesquisarFuncionarioIdTest() {
	     	
		Optional<List<Lancamento>> l = this.lancamentoService.findByFuncionarioId(1L);
		
		assertTrue(l.isPresent());
		
	}
	
	@Test
	public void devePesquisarFuncionarioId2Test() {
		
		Page<Lancamento> l = this.lancamentoService.findByFuncionarioId(1l, PageRequest.of(0, 10) ); 
		
		assertNotNull(l);
		
	}
	
	
	@Test
	public void devePersisirLancamentoTest() {
		Lancamento l = this.lancamentoService.persistir(new Lancamento());
		
		assertNotNull(l);
	}
	

}
