package com.loja.lojavirtual.dto.input;

import lombok.Data;
@Data
public class ItemCompraInput {
	

	//@NotNull
	private Long produtoId;

	//@NotNull
	//@PositiveOrZero
	private Integer quantidade;

}
