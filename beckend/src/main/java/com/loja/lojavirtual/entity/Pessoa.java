package com.loja.lojavirtual.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	@Setter(value = AccessLevel.NONE)
	private List<PermissaoPessoa> permissoes;

	private String nome;

	private String cpf;

	private String email;

	@Embedded
	private Endereco endereco;

	public void setPermissaoPessoas(List<PermissaoPessoa> pp) {
		for (PermissaoPessoa p : pp) {
			p.setPessoa(this);
		}
		this.permissoes = pp;
	}

	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dataCriacao;

	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dataAtualizacao;
}
