package com.pontointeligente.controller.converter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Optional;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.pontointeligente.domain.Empresa;
import com.pontointeligente.domain.Funcionario;
import com.pontointeligente.domain.Lancamento;
import com.pontointeligente.dto.CadastroPfDto;
import com.pontointeligente.dto.CadastroPjDto;
import com.pontointeligente.dto.EmpresaDto;
import com.pontointeligente.dto.FuncionarioDto;
import com.pontointeligente.dto.LancamentoDto;
import com.pontointeligente.enuns.PerfilEnum;
import com.pontointeligente.enuns.TipoEnum;
import com.pontointeligente.services.LancamentoService;
import com.pontointeligente.utils.DateUtils;

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
	
	
	public LancamentoDto converterLancamentoParaLancamentoDto(Lancamento lancamento) {
		LancamentoDto dto = new LancamentoDto();
		dto.setData(DateUtils.dateFormatAmerica.format(lancamento.getData()));
		dto.setFuncionarioId(lancamento.getFuncionario().getId());
		dto.setDescricao(lancamento.getDescricao());
		dto.setLocalizacao(lancamento.getLocalizacao());
		dto.setTipo(lancamento.getTipo().toString());
		dto.setId(Optional.of(lancamento.getId()));
		return dto;
	}
	
	
	public Lancamento converterDtoParaLancamento(LancamentoDto lancamentoDto, LancamentoService lancamentoService, BindingResult result) throws ParseException {
		
		Lancamento lancamento = new Lancamento();

		if (lancamentoDto.getId().isPresent()) {
			Optional<Lancamento> lanc = lancamentoService.buscarPorId(lancamentoDto.getId().get());
			if (lanc.isPresent()) {
				lancamento = lanc.get();
			} else {
				result.addError(new ObjectError("lancamento", "Lançamento não encontrado."));
			}
		} else {
			lancamento.setFuncionario(new Funcionario());
			lancamento.getFuncionario().setId(lancamentoDto.getFuncionarioId());
		}

		lancamento.setDescricao(lancamentoDto.getDescricao());
		lancamento.setLocalizacao(lancamentoDto.getLocalizacao());
		lancamento.setData(DateUtils.dateFormatAmerica.parse(lancamentoDto.getData()));

		if (EnumUtils.isValidEnum(TipoEnum.class, lancamentoDto.getTipo())) {
			lancamento.setTipo(TipoEnum.valueOf(lancamentoDto.getTipo()));
		} else {
			result.addError(new ObjectError("tipo", "Tipo inválido."));
		}

		return lancamento;
	}
	
	
	
	
	
}
