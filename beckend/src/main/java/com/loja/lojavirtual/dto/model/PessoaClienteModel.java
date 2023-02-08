package com.loja.lojavirtual.dto.model;

import java.util.Set;

import com.loja.lojavirtual.entity.Endereco;
import com.loja.lojavirtual.entity.Permissao;

import lombok.Data;

@Data
public class PessoaClienteModel {
	

	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String email;
	
	private Endereco endereco;
	
	private Set<Permissao> permissoes;
	

}
