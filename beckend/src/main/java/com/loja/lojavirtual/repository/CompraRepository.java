package com.loja.lojavirtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.lojavirtual.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	List<Compra> findByClienteId (Long clienteId);
	
	Optional<Compra> findByCodigo(String codigo);
	
	//@Query("from Compra p join fetch p.cliente join fetch p.categoria r join fetch r.marca")
	//List<Compra> findAll();
	
	

}
