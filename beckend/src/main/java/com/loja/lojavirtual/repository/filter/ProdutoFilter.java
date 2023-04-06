package com.loja.lojavirtual.repository.filter;

import java.math.BigDecimal;

import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.entity.Marca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFilter {
	

	
	private String nome;
	
	private BigDecimal valor_Venda;
	
	private Marca marcaId;
	
	private Categoria categoriaId;



}
