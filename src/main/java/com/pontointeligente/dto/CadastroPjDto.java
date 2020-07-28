package com.pontointeligente.dto;


import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CadastroPjDto extends PessoaDto {
	
	
	private String razaoSocial;
	
	@NotEmpty(message = "Razão social não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Razão social deve conter entre 5 e 200 caracteres.")
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Override
	public String toString() {
		return "CadastroPJ [razaoSocial=" + razaoSocial + ", getId()=" + getId() + ", getNome()=" + getNome()
				+ ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha() + ", getCpf()=" + getCpf()
				+ ", getCnpj()=" + getCnpj() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	

}
