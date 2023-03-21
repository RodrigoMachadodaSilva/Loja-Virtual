package com.loja.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.repository.ProdutoRepository;

import exception.EntidadeEmUsoException;
import exception.NegocioException;
import exception.ProdutoNaoEncontradoException;
import com.loja.lojavirtual.service.CategoriaService;

@Service
public class ProdutoService {

	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";

	@Autowired
	private CategoriaService categoriaService;;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listar(Long categoriaID) {
		return produtoRepository.findByCategoriaId(categoriaID);
	}

	public Produto buscar(Long produtoId, Long categoriaId) {
		Produto produto = produtoRepository.buscarPorId(produtoId, categoriaId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

		return produto;
	}

	public Produto salvar(Long categoriaId, Produto produto) {
		validarCategoriaProdutoExiste(categoriaId);
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaId);
		validarProdutoDuplicado(produto);
		produto.setCategoria(categoria);
		return produtoRepository.save(produto);
	}

	@Transactional
	public void excluir(Long categoriaId, Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
			produtoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}

	private void validarCategoriaProdutoExiste(Long categoriaId) {
		if (categoriaId == null) {
			throw new NegocioException("A categoria informada não pode ser nula");
		}

	}

	private void validarProdutoDuplicado(Produto produto) {
		Optional<Produto> produtoExiste = produtoRepository.findByCategoriaIdAndNomeAndDescricao(
				produto.getCategoria().getId(), produto.getNome(), produto.getDescricao());
		if (produtoExiste.isPresent()) {
			throw new NegocioException(
					String.format("O produto de nome %s já está salvo em nosso sistema", produto.getNome()));
		}
	}

	protected Produto validarProdutoCompraExiste(Long produtoId) {
		Optional<Produto> produtoExiste = produtoRepository.findById(produtoId);
		if (produtoExiste.isEmpty()) {
			throw new ProdutoNaoEncontradoException(produtoId);
		}
		return produtoExiste.get();
	}

	public Produto buscarPorId(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}

}
