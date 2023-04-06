package com.loja.lojavirtual.dto.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoModel {

	private Long id;

	private String nome;

	private String descricao;

	private BigDecimal valor_Venda;
	
	private Integer quantidade_Estoque;

	private MarcaIdModel marca;

	private CategoriaIdModel categoria;
	

}
