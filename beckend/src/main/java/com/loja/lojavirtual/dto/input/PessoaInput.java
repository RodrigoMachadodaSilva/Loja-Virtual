package com.loja.lojavirtual.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.loja.lojavirtual.entity.Cidade;
import com.loja.lojavirtual.entity.Endereco;

import lombok.Data;

@Data
public class PessoaInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Valid
	private Endereco endereco;
	


}
