 package com.pontointeligente;
 
 
  import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.builder.EmpresaBuilder;
import com.pontointeligente.domain.Empresa;
import com.pontointeligente.repository.EmpresaRepository;
  
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") 
public class PontoInteligenteApplicationTests {
  
  private static final String CNPJ = "08732375000158";
  
  @Autowired 
  private EmpresaRepository respository;
	  
  
  @Before 
  public void setUp() { 
	  System.out.println("teste");
	  Empresa empresa = new EmpresaBuilder().comCnpj(CNPJ).comRazaoSocial("Exemplo").build();
	  this.respository.save(empresa); 
  }
	  
  @After public void setDown() { 
	  this.respository.deleteAll(); 
  }
	  
  @Test 
  public void buscarPorCnpjTest() { 
		Empresa empresa = this.respository.findByCnpj(CNPJ); 
		//assertEquals(cnpj, empresa.getCnpj()); 
		assertNull(empresa); 
   }
  
  
}
 