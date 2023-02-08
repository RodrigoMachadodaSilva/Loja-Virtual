package com.loja.lojavirtual.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.model.PessoaClienteModel;
import com.loja.lojavirtual.entity.Pessoa;

@Component
public class PessoaClienteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PessoaClienteModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaClienteModel.class);
	}

	public List<PessoaClienteModel> toCollectionModel(List<Pessoa> pessoas) {
		return pessoas.stream().map(pessoa -> toModel(pessoa)).collect(Collectors.toList());
	}

}
