package com.loja.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
	
	Marca findByNome (String nome);

}
