package com.loja.lojavirtual.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal valor_Custo;
	
	private BigDecimal valor_Venda;
	
	private Integer quantidade_Estoque;
	
	@ManyToOne
	@JoinColumn(name = "marca_Id")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "categoria_Id")
	private Categoria categoria;

}
