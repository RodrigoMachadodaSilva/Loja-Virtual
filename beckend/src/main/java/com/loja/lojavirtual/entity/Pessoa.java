package com.loja.lojavirtual.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

	@Embedded
	private Endereco endereco;



	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dataCriacao;

	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dataAtualizacao;
}
