package com.loja.lojavirtual.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.input.CategoriaInput;
import com.loja.lojavirtual.entity.Categoria;

@Component
public class CategoriaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Categoria toDomainObject(CategoriaInput categoriaInput) {
		return modelMapper.map(categoriaInput, Categoria.class);
	}

	public void copyToDomainObject(CategoriaInput categoriaInput, Categoria categoria) {
		modelMapper.map(categoriaInput, categoria);
	}

}
