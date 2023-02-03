package com.loja.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojavirtual.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
