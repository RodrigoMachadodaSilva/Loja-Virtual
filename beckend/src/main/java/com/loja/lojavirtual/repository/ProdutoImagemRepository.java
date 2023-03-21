package com.loja.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.ProdutoImagem;

public interface ProdutoImagemRepository extends JpaRepository<ProdutoImagem, Long> {
	
	public List<ProdutoImagem> findByProdutoId(Long id);

}
