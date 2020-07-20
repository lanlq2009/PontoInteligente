package com.pontointeligente.builder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.enuns.PerfilEnum;

public class FuncionarioBuilder {
	
    private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal valorHora;
	private Float qtdHorasTrabalhoDia;
	private Float qtdHorasAlmoco;
	private PerfilEnum perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	private List<Lancamento> lancamentos;
	
	public FuncionarioBuilder comId(Long id) {
		this.id = id;
		return this;
	}
		
	public FuncionarioBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
		
	public FuncionarioBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public FuncionarioBuilder comSenha(String senha) {
		this.senha = senha;
		return this;
	}
	
	public FuncionarioBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public FuncionarioBuilder comValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
		return this;
	}
	
	public FuncionarioBuilder comQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
		return this;
	}
	
	public FuncionarioBuilder comQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
		return this;
	}
		
	public FuncionarioBuilder comPerfilFuncionario(PerfilEnum perfil) {
		this.perfil = perfil;
		return this;
	}
	
	public FuncionarioBuilder comEmpresa(Empresa empresa) {
		this.empresa = empresa;
		return this;
	}
	
	
	public FuncionarioBuilder comLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
		return this;
	}
	
	public Funcionario build() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(this.id);
		funcionario.setNome(this.nome);
		funcionario.setEmail(this.email);
		funcionario.setSenha(this.senha);
		funcionario.setCpf(this.cpf);
		funcionario.setValorHora(this.valorHora);
		funcionario.setQtdHorasTrabalhoDia(this.qtdHorasTrabalhoDia);
		funcionario.setQtdHorasAlmoco(this.qtdHorasAlmoco);
		funcionario.setPerfil(this.perfil);
		funcionario.setLancamentos(this.lancamentos);
		return funcionario;
	}
	
	

}
