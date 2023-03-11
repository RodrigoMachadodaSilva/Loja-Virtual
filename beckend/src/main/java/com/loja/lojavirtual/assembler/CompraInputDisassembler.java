package com.loja.lojavirtual.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.input.CompraInput;
import com.loja.lojavirtual.entity.Compra;

@Component
public class CompraInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Compra toDomainObject(CompraInput compraInput) {
		return modelMapper.map(compraInput, Compra.class);
	}

	public void copyToDomainObject(CompraInput compraInput, Compra compra) {
		modelMapper.map(compraInput, compra);
	}

}
