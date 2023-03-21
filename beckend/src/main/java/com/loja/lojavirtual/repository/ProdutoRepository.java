package com.loja.lojavirtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.lojavirtual.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByCategoriaId (Long categoriaId);
	
	@Query("Select prod from Produto prod where prod.id = :produtoId and prod.categoria.id = :categoriaId")
	Optional<Produto> buscarPorId (Long produtoId, Long categoriaId );
	
	Optional<Produto> findByCategoriaIdAndNomeAndDescricao(Long categoriaId, String nome, String descricao);

}
