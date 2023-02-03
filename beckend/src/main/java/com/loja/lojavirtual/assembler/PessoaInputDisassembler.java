package com.loja.lojavirtual.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.input.PessoaInput;
import com.loja.lojavirtual.entity.Pessoa;

@Component
public class PessoaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pessoa toDomainObject(PessoaInput pessoaInput) {
		return modelMapper.map(pessoaInput, Pessoa.class);
	}

	public void copyToDomainObject(PessoaInput pessoaInput, Pessoa pessoa) {
		modelMapper.map(pessoaInput, pessoa);
	}

}
