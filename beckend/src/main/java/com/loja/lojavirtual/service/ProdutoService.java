package com.loja.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.repository.ProdutoRepository;

import exception.EntidadeEmUsoException;
import exception.NegocioException;
import exception.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {

	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";

	@Autowired
	private CategoriaService categoriaService;;

	@Autowired
	private ProdutoRepository produtoRepository;
	

	public List<Produto> listar(Long categoriaID) {
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaID);
		return produtoRepository.findByCategoriaId(categoria.getId());
	}

	public List<Produto> listarDisponiveis(Long categoriaID) {
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaID);
		return produtoRepository.buscarDisponivelPorId(categoria.getId());

	}

	public Produto buscar(Long produtoId, Long categoriaId) {
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaId);
		Produto produto = produtoRepository.buscarTodosPorId(produtoId, categoria.getId())
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, categoriaId));

		return produto;
	}

	public Produto salvar(Long categoriaId, Produto produto) {
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaId);
		validarProdutoDuplicado(produto);
		produto.setCategoria(categoria);
		return produtoRepository.save(produto);
	}

	@Transactional
	public void excluir(Long categoriaId, Long produtoId) {
		try {
			categoriaService.buscarOuFalhar(categoriaId);
			produtoRepository.deleteById(produtoId);
			produtoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId, categoriaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}

	public void validarProdutoDuplicado(Produto produto) {
		Optional<Produto> produtoExiste = produtoRepository.findByCategoriaIdAndNomeAndDescricao(
				produto.getCategoria().getId(), produto.getNome(), produto.getDescricao());
		if (produtoExiste.isPresent()) {
			throw new NegocioException(
					String.format("O produto de nome %s já está salvo em nosso sistema", produto.getNome()));
		}
	}

	protected Produto validarProdutoCompraExiste(Long produtoId) {
		Optional<Produto> produtoExiste = produtoRepository.findById(produtoId);
		if (produtoExiste.isEmpty() && produtoExiste.get().getQuantidade_Estoque() > 0) {
			throw new ProdutoNaoEncontradoException(produtoId);
		}
		return produtoExiste.get();
	}

	public Produto buscarPorId(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}

}
