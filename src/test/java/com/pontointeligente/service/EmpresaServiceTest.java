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

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.repository.EmpresaRepository;
import com.pontointeligente.services.EmpresaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") 
public class EmpresaServiceTest {
	
	
	@MockBean
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	private static final String CNPJ = "51463645000100";
	
	@Before
	public void setUp() {
		BDDMockito.given(this.empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
	}
	
	
	@Test
	public void buscaEmpresaPorCnpjTest() {
		Optional<Empresa> empresa = this.empresaService.buscaPorCnpj(CNPJ);
		assertTrue(empresa.isPresent());
	}
	
	@Test
	public void persitirEmpresaTest() {
		Empresa empresa = this.empresaService.persitir(new Empresa());
		assertNotNull(empresa);
	}
	

}
