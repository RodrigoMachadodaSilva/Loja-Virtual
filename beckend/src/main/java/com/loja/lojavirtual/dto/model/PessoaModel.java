package com.loja.lojavirtual.dto.model;

import com.loja.lojavirtual.entity.Cidade;
import com.loja.lojavirtual.entity.Endereco;

import lombok.Data;

@Data
public class PessoaModel {
	

	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String email;
	
	private Endereco endereco;
	

}
