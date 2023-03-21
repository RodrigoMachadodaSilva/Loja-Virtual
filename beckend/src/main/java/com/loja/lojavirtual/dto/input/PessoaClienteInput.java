package com.loja.lojavirtual.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PessoaClienteInput {
	
	@NotBlank
	private Long id;
	
	//@NotBlank
	//private String cpf;
	
	//@NotBlank
	//private String email;
	
	//@NotBlank
	//@Valid
	//private Endereco endereco;
	
	//private Set<Permissao> permissoes;
	


}
