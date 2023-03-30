package com.loja.lojavirtual.dto.input;

import com.loja.lojavirtual.entity.Estado;

import lombok.Data;

@Data
public class CidadeInput {

	private String nome;
	
	private Estado estado;

}
