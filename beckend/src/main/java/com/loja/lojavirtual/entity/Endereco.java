package com.loja.lojavirtual.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	@NotBlank
	@Column(name = "endereco_cep")
	private String cep;
	
	@NotBlank
	@Column(name = "endereco_logradouro")
	private String logradouro;
	
	@NotBlank
	@Column(name = "endereco_numero")
	private String numero;

	@NotBlank
	@Column(name = "endereco_complemento")
	private String complemento;

	@NotBlank
	@Column(name = "endereco_bairro")
	private String bairro;

	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;

}
