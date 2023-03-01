package com.loja.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	Estado findByNome (String nome);

}
