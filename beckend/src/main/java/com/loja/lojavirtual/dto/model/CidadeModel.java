package com.loja.lojavirtual.dto.model;

import lombok.Data;

@Data
public class CidadeModel {

	private Long id;

	private String nome;
	
	private EstadoModel estadoModel;
}
