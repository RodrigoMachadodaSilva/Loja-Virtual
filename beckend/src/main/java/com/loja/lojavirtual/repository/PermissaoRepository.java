package com.loja.lojavirtual.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	

	Set<Permissao> findByNome(String nome);

}
