package com.loja.lojavirtual.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.lojavirtual.dto.input.MarcaInput;
import com.loja.lojavirtual.entity.Marca;
	
	@Component
	public class MarcaInputDisassembler {

		@Autowired
		private ModelMapper modelMapper;
		
		public Marca toDomainObject(MarcaInput MarcaInput) {
			return modelMapper.map(MarcaInput, Marca.class);
		}
		
		public void copyToDomainObject(MarcaInput marcaInput, Marca marca) {
			modelMapper.map(marcaInput, marca);
		}
		
	}


