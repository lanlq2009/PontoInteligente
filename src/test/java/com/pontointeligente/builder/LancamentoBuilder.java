package com.pontointeligente.builder;

import java.util.Date;

import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.enuns.TipoEnum;

public class LancamentoBuilder {
	
    private Long id;
	private Date data;
	private String descricao;
	private String localizacao;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private TipoEnum tipo;
	private Funcionario funcionario;
	
	public LancamentoBuilder comId(Long id) {
		this.id = id;
		return this;
	}
	
	public LancamentoBuilder comData(Date data) {
		this.data = data;
		return this;
	}
	
	public LancamentoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public LancamentoBuilder comLocalizacao(String localizacao) {
		this.localizacao = localizacao;
		return this;
	}
	
	public LancamentoBuilder comTipoEnum(TipoEnum tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public LancamentoBuilder comFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		return this;
	}
	
	public Lancamento build() {
		Lancamento l = new Lancamento();
		l.setId(this.id);
		l.setData(this.data);
		l.setDescricao(this.descricao);
		l.setLocalizacao(this.localizacao);
		l.setTipo(this.tipo);
		l.setFuncionario(this.funcionario);
		return l;
	}
	

}
