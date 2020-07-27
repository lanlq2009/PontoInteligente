package com.pontointeligente.model;

import java.util.Optional;

public class CadastroPF extends Pessoa {
	
	private Optional<String> valorHora = Optional.empty();
	
	private Optional<String> qtdHorasTrabalhadasPorDia = Optional.empty();
	
	private Optional<String> qtdHorasAlmoco = Optional.empty();

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasTrabalhadasPorDia() {
		return qtdHorasTrabalhadasPorDia;
	}

	public void setQtdHorasTrabalhadasPorDia(Optional<String> qtdHorasTrabalhadasPorDia) {
		this.qtdHorasTrabalhadasPorDia = qtdHorasTrabalhadasPorDia;
	}

	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	@Override
	public String toString() {
		return "CadastroPF [valorHora=" + valorHora + ", qtdHorasTrabalhadasPorDia=" + qtdHorasTrabalhadasPorDia
				+ ", qtdHorasAlmoco=" + qtdHorasAlmoco + ", getId()=" + getId() + ", getNome()=" + getNome()
				+ ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha() + ", getCpf()=" + getCpf()
				+ ", getCnpj()=" + getCnpj() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	

}
