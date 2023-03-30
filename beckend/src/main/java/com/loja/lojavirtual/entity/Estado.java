package com.loja.lojavirtual.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "estado")
public class Estado {

	@Id
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String nome;

}
