package com.loja.lojavirtual.dto.input;

import java.util.List;

import com.loja.lojavirtual.entity.Endereco;
import com.loja.lojavirtual.entity.Permissao;

import lombok.Data;

@Data
public class PessoaInput {
	
	
	private String nome;
	
	private List<Permissao> permissoes;
	

	private String cpf;
	

	private String email;
	
	
	private Endereco endereco;
	

	


}
