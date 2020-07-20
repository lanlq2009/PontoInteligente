package com.pontointeligente.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.builder.FuncionarioBuilder;
import com.pontointeligente.builder.LancamentoBuilder;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.enuns.PerfilEnum;
import com.pontointeligente.enuns.TipoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	
	private final static String CPF = "01234567890";
		
	private final static String NOME= "Ash Tyler";
		
	private final static String EMAIL = "atyler@uss.gov.un";
	
	private Funcionario funcionario;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Before
	public void setUp() {
		
		this.funcionario = new FuncionarioBuilder().
                comCpf(CPF).
                comNome(NOME).
                comEmail(EMAIL).
                comPerfilFuncionario(PerfilEnum.ROLE_USUARIO).
                comSenha("123456").build();

        this.funcionario = this.funcionarioRepository.save(funcionario);
        
        Lancamento lancamento = new LancamentoBuilder().
        		                    comData(new Date()).
        		                    comDescricao("nhee").
        		                    comLocalizacao("floresta").
        		                    comTipoEnum(TipoEnum.INICIO_TRABALHO).
        		                    comFuncionario(this.funcionario).build();
        
        this.lancamentoRepository.save(lancamento);
        
	}
	
	@After
	public void setDown() {
		
		this.lancamentoRepository.deleteAll();
		
		this.funcionarioRepository.deleteAll();
	}
	
	
	@Test
	public void pesquisarFuncionarioIdTest() {
		
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(this.funcionario.getId());
		
		assertEquals(1, lancamentos.size());
		
		
	}
     
	

}
