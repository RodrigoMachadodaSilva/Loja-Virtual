package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.repository.ProdutoRepository;

import exception.CidadeNaoEncontradaException;
import exception.EntidadeEmUsoException;
import exception.MarcaNaoEncontradaException;
import exception.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {
	
	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";


	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	public Produto buscar(Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new MarcaNaoEncontradaException(produtoId));
	}
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public void excluir(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
			produtoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}
}
