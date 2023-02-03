package com.loja.lojavirtual.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String email;
	
	@Embedded
	private Endereco endereco;
	
    @ManyToOne
    @JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	//@ManyToMany
	//private List<Permissao> permissao;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date dataCriacao;

	//@Temporal(TemporalType.TIMESTAMP)
	//private Date dataAtualizacao;
}
