package com.loja.lojavirtual.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.model.CompraModel;
import com.loja.lojavirtual.entity.Compra;

@Component
public class CompraModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CompraModel toModel(Compra compra) {
		return modelMapper.map(compra, CompraModel.class);
	}

	public List<CompraModel> toCollectionModel(List<Compra> compras) {
		return compras.stream().map(compra -> toModel(compra)).collect(Collectors.toList());
	}

}
