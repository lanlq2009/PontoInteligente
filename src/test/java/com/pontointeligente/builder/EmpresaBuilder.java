package com.pontointeligente.builder;

import java.util.Date;
import java.util.List;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;

public class EmpresaBuilder {

    private Long id;
	
	private String razaoSocial;
	
	private String cnpj;
	
	private Date dataCriacao;
	
	private Date dataAtualizacao;
	
	private List<Funcionario> funcionarios;
  
	public EmpresaBuilder comId(Long id) {
		this.id = id;
		return this;
	}
	
	public EmpresaBuilder comRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		return this;
	}
	
	public EmpresaBuilder comCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}
	
	public EmpresaBuilder comDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
		return this;
	}
		
	public EmpresaBuilder comDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
		return this;
	}
	
	public EmpresaBuilder comListaFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
		return this;
	}
	
	public Empresa build() {
		Empresa empresa = new Empresa();
		empresa.setId(this.id);
		empresa.setCnpj(this.cnpj);
		empresa.setRazaoSocial(this.razaoSocial);
		empresa.setDataCriacao(this.dataCriacao);
		empresa.setDataAtualizacao(this.dataAtualizacao);
		empresa.setFuncionarios(this.funcionarios);
		return empresa;
	}
	
	
	
	

}
