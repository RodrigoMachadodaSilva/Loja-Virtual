package com.loja.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
