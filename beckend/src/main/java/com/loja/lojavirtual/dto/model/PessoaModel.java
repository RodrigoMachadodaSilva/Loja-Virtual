package com.loja.lojavirtual.dto.model;

import java.util.List;

import com.loja.lojavirtual.entity.Endereco;
import com.loja.lojavirtual.entity.Permissao;

import lombok.Data;

@Data
public class PessoaModel {
	

	private Long id;
	
	
	
	private String nome;
	
	private String cpf;
	
	private String email;
	
	private Endereco endereco;
	
	//private List<Permissao> permissoes;
	

	

}
