package com.loja.lojavirtual.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "permissao")
@Data
public class Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	//@Temporal(TemporalType.TIMESTAMP)
	//private Date dataCriacao;

	//@Temporal(TemporalType.TIMESTAMP)
	//private Date dataAtualizacao ;
}
