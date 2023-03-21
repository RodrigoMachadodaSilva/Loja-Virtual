package com.loja.lojavirtual.entity;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_compra")
public class ItemCompra {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal precoUnitario;
	
	private BigDecimal precoTotal;
	
	private Integer quantidade;
	
    @JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Compra compra;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	

	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = getProduto().getValor_Venda();
		Integer quantidade = this.getQuantidade();

		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}

		if (quantidade == null) {
			quantidade = 0;
		}

		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
	
}
