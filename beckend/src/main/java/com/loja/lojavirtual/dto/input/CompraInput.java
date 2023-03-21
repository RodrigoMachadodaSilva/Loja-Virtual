package com.loja.lojavirtual.dto.input;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.loja.lojavirtual.entity.Endereco;

import lombok.Data;
@Data
public class CompraInput {
	
	private BigDecimal taxaFrete;
	
	
	//@Valid
	//@NotNull
	private Endereco enderecoEntrega;
	
	//@Valid
	//@NotNull
	//private FormaPagamentoIdInput formaPagamento;
	
	//@Valid
	//@Size(min = 1)
	//@NotNull
	private List<ItemCompraInput> itens;
	
	private PessoaClienteInput cliente;
	

}
