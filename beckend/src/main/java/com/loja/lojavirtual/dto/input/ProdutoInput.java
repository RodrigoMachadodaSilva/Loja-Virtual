package com.loja.lojavirtual.dto.input;

import java.math.BigDecimal;

import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.entity.Marca;

import lombok.Data;

@Data
public class ProdutoInput {

	private String nome;

	private String descricao;

	private BigDecimal valor_Custo;

	private BigDecimal valor_Venda;

	private MarcaInput marcaInput;

	private CategoriaInput categoriaInput;

}
