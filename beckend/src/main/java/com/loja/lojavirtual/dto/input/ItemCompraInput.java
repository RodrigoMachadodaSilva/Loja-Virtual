package com.loja.lojavirtual.dto.input;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
@Data
public class ItemCompraInput {

	//@NotNull
	private Long produtoId;

	//@NotNull
	//@PositiveOrZero
	private Integer quantidade;

}
