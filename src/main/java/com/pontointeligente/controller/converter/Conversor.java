package com.pontointeligente.controller.converter;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.enuns.PerfilEnum;
import com.pontointeligente.model.CadastroPJ;
import com.pontointeligente.utils.PasswordUtils;

public class Conversor {
	
	public Empresa converterEmpresa(CadastroPJ cadastroPj) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPj.getCnpj());
		empresa.setRazaoSocial(cadastroPj.getRazaoSocial());
		return empresa;
	}
	
	public Funcionario converterDtoParaFuncionario(CadastroPJ cadastroPJDto, Boolean isPJ) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		if(isPJ) {
		   funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		}else {
			funcionario.setPerfil(PerfilEnum.ROLE_USUARIO); 	
		}
		funcionario.setSenha(PasswordUtils.getEncrypt(cadastroPJDto.getSenha()));
		return funcionario;
	}

	public CadastroPJ converterCadastroPJ(Funcionario funcionario) {
		CadastroPJ cadastroPJ = new CadastroPJ();
		cadastroPJ.setId(funcionario.getId());
		cadastroPJ.setNome(funcionario.getNome());
		cadastroPJ.setEmail(funcionario.getEmail());
		cadastroPJ.setCpf(funcionario.getCpf());
		cadastroPJ.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJ.setCnpj(funcionario.getEmpresa().getCnpj());
		return cadastroPJ;
	}
	
	
}
