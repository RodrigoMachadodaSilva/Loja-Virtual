package com.loja.lojavirtual.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.model.MarcaModel;
import com.loja.lojavirtual.entity.Marca;

@Component
public class MarcaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public MarcaModel toModel(Marca marca) {
		return modelMapper.map(marca, MarcaModel.class);
	}
	
	public List<MarcaModel> toCollectionModel(List<Marca> marcas) {
		return marcas.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}
	
}
