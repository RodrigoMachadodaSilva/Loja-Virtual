package com.loja.lojavirtual.dto.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.loja.lojavirtual.entity.Endereco;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompraModel {

	private String codigo;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;

	private OffsetDateTime dataCriacao;

	private PessoaClienteModel cliente;

	// private FormaPagamentoModel formaPagamento;

	private Endereco enderecoEntrega;

	private List<ItemCompraModel> itens;

}
