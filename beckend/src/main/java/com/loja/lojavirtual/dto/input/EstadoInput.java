package com.loja.lojavirtual.dto.input;

import com.loja.lojavirtual.entity.Estado;

import lombok.Data;

@Data
public class EstadoInput {
	
	private String nome;
	
	private String sigla;
	
	private Estado estado;

}
