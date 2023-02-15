package com.loja.lojavirtual.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	@JoinTable(name = "pessoa_permissao")
	private Set<Permissao> permissoes;

	private String nome;

	private String cpf;

	private String email;

	private String senha;

	private String codigoRecuperacaoSenha;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvioCodigo;

	@Embedded
	private Endereco endereco;

	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dataCriacao;

	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dataAtualizacao;
}
