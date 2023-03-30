package com.loja.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	Cidade findByNome(String nome);

}
