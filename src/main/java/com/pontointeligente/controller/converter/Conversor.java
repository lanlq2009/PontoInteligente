package com.pontointeligente.controller.converter;

import java.math.BigDecimal;
import java.util.Optional;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.dto.CadastroPfDto;
import com.pontointeligente.dto.CadastroPjDto;
import com.pontointeligente.dto.EmpresaDto;
import com.pontointeligente.dto.FuncionarioDto;
import com.pontointeligente.enuns.PerfilEnum;

public class Conversor {
	
	public Empresa converterEmpresa(CadastroPjDto cadastroPj) {
		Empresa empresa = new Empresa();
		empresa.setId(null);
		empresa.setCnpj(cadastroPj.getCnpj());
		empresa.setRazaoSocial(cadastroPj.getRazaoSocial());
		return empresa;
	}
	
	public Funcionario converterDtoParaFuncionario(CadastroPjDto cadastroPJDto, Boolean isPJ) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		if(isPJ) {
		   funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		}else {
			funcionario.setPerfil(PerfilEnum.ROLE_USUARIO); 	
		}
		//funcionario.setSenha(PasswordUtils.getEncrypt(cadastroPJDto.getSenha()));
		funcionario.setSenha(cadastroPJDto.getSenha());
		return funcionario;
	}
	

	public CadastroPjDto converterCadastroPJ(Funcionario funcionario) {
		CadastroPjDto cadastroPJ = new CadastroPjDto();
		cadastroPJ.setId(funcionario.getId());
		cadastroPJ.setNome(funcionario.getNome());
		cadastroPJ.setEmail(funcionario.getEmail());
		cadastroPJ.setCpf(funcionario.getCpf());
		cadastroPJ.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJ.setCnpj(funcionario.getEmpresa().getCnpj());
		return cadastroPJ;
	}
		
	
	public Funcionario converterCadastroPfParaFuncionario(CadastroPfDto cadastroPF) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPF.getNome());
		funcionario.setEmail(cadastroPF.getEmail());
		funcionario.setCpf(cadastroPF.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO); 	
		//funcionario.setSenha(PasswordUtils.getEncrypt(cadastroPJDto.getSenha()));
		funcionario.setSenha(cadastroPF.getSenha());
		cadastroPF.getQtdHorasTrabalhadasPorDia().ifPresent(qtdHorasTrabalhadasPorDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabalhadasPorDia)));
		cadastroPF.getQtdHorasAlmoco().ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		cadastroPF.getValorHora().ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));
		return funcionario;
	}
	
	
	public CadastroPfDto converterFuncionarioParaCadastroPf(Funcionario funcionario) {
		CadastroPfDto cadastro = new CadastroPfDto();
		cadastro.setCnpj(funcionario.getEmpresa().getCnpj());
		cadastro.setCpf(funcionario.getCpf());
		cadastro.setEmail(funcionario.getEmail());
		cadastro.setId(funcionario.getId());
		cadastro.setNome(funcionario.getNome());
	    funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> cadastro.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
	    funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(qtdHorasTrabalhoDia -> cadastro.setQtdHorasTrabalhadasPorDia(Optional.of(Float.toString(qtdHorasTrabalhoDia))));
		funcionario.getValorHoraOpt().ifPresent(vlHora -> cadastro.setValorHora(Optional.of(vlHora.toString())));
		return cadastro;
	}
	
	
	public EmpresaDto converterEmpresaParaEmpresaDTO(Empresa empresa) {
		EmpresaDto dto = new EmpresaDto();
		dto.setId(empresa.getId());
		dto.setCnpj(empresa.getCnpj());
		dto.setRazaoSocial(empresa.getRazaoSocial());
		return dto;
	}
	
	
	public FuncionarioDto converterFuncionarioParaFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto dto = new FuncionarioDto();
		dto.setEmail(funcionario.getEmail());
		dto.setId(funcionario.getId());
		dto.setNome(funcionario.getNome());
		funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> dto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(qtdHorasTrabalhoDia -> dto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabalhoDia))));
		funcionario.getValorHoraOpt().ifPresent(valorHora -> dto.setValorHora(Optional.of(valorHora.toString())));
		return dto;
	}
	
	
	
}
