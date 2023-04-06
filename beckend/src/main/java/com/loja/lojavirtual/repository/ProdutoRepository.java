package com.loja.lojavirtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.loja.lojavirtual.entity.Compra;
import com.loja.lojavirtual.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

	List<Produto> findByCategoriaId(Long categoriaId);

	@Query("Select prod from Produto prod where prod.id = :produtoId and prod.categoria.id = :categoriaId")
	Optional<Produto> buscarTodosPorId(Long produtoId, Long categoriaId);

	Optional<Produto> findByCategoriaIdAndNomeAndDescricao(Long categoriaId, String nome, String descricao);

	// @Query("Delete prod from Produto prod where prod.id = :produtoId and
	// prod.categoria.id = :categoriaId")
	// Produto deletarPorId (Long produtoId, Long categoriaId );

	@Query("Select prod from Produto prod where prod.categoria.id = :categoriaId and prod.quantidade_Estoque >0")
	List<Produto> buscarDisponivelPorId(Long categoriaId);


}
