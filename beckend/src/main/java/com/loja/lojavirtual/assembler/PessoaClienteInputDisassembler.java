package com.loja.lojavirtual.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.input.PessoaClienteInput;
import com.loja.lojavirtual.entity.Pessoa;

@Component
public class PessoaClienteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pessoa toDomainObject(PessoaClienteInput pessoaInput) {
		return modelMapper.map(pessoaInput, Pessoa.class);
	}

	public void copyToDomainObject(PessoaClienteInput pessoaInput, Pessoa pessoa) {
		modelMapper.map(pessoaInput, pessoa);
	}

}
