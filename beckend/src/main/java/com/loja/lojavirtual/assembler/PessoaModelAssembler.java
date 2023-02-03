package com.loja.lojavirtual.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.model.PessoaModel;
import com.loja.lojavirtual.entity.Pessoa;

@Component
public class PessoaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PessoaModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaModel.class);
	}

	public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas) {
		return pessoas.stream().map(pessoa -> toModel(pessoa)).collect(Collectors.toList());
	}

}
