package com.loja.lojavirtual.dto.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoInput {

	private String nome;

	private String descricao;

	private BigDecimal valor_Custo;

	private BigDecimal valor_Venda;

	private MarcaIdInput marca;

	private CategoriaIdInput categoria;

}
