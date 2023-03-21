package com.loja.lojavirtual.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loja.lojavirtual.dto.input.ItemCompraInput;
import com.loja.lojavirtual.entity.ItemCompra;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(ItemCompraInput.class, ItemCompra.class)
				.addMappings(mapper -> mapper.skip(ItemCompra::setId));

		return modelMapper;

	}

}