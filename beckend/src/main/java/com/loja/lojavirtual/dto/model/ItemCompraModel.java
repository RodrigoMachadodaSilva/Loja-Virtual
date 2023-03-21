package com.loja.lojavirtual.dto.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCompraModel {
	
	private Long produtoId;
	
	private String produtoNome;
	
	private Integer quantidade;
	
	private BigDecimal precoUnitario;
	
	private BigDecimal precoTotal;
	

	


}
