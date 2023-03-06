package com.loja.lojavirtual.dto.input;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.loja.lojavirtual.entity.Endereco;
import com.loja.lojavirtual.entity.Permissao;

import lombok.Data;

@Data
public class PessoaClienteInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Valid
	private Endereco endereco;
	
	//private Set<Permissao> permissoes;
	


}
