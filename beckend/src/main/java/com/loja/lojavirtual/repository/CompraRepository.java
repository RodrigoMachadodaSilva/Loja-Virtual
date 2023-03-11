package com.loja.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	List<Compra> findByClienteId (Long clienteId);
	
	

}
