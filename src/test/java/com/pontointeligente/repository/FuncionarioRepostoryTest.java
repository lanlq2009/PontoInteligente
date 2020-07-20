package com.pontointeligente.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.builder.FuncionarioBuilder;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.enuns.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") 
public class FuncionarioRepostoryTest {
	
	private final static String CPF = "01234567890";
	
	private final static String NOME= "Ash Tyler";
	
	private final static String EMAIL = "atyler@uss.gov.un";
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Before
	public void setUp() {
		Funcionario funcionario = new FuncionarioBuilder().
				                      comCpf(CPF).
				                      comNome(NOME).
				                      comEmail(EMAIL).
				                      comPerfilFuncionario(PerfilEnum.ROLE_USUARIO).
				                      comSenha("123456").
				                      build();
		
		this.funcionarioRepository.save(funcionario);
	
	}
	
	@After
	public void setDown() {
		this.funcionarioRepository.deleteAll();
	}
	
	@Test
	public void devePesquisarCpfFuncionarioTest() {
		Funcionario f = this.funcionarioRepository.findByCpf(CPF);
		assertEquals(CPF, f.getCpf());
	}

}
