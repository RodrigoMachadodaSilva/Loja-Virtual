package com.loja.lojavirtual.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.entity.ProdutoImagem;
import com.loja.lojavirtual.repository.ProdutoImagemRepository;
import com.loja.lojavirtual.repository.ProdutoRepository;

import exception.EntidadeEmUsoException;
import exception.ProdutoImagemNaoEncontradaException;

@Service
public class ProdutoImagemService {

	private static final String MSG_IMAGEM_EM_USO = "Imagem de código %d não pode ser removido, pois está em uso";

	@Autowired
	private ProdutoImagemRepository produtoImagemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoImagem> listar() {
		return produtoImagemRepository.findAll();
	}

	public ProdutoImagem buscar(Long produtoImagemId) {
		return produtoImagemRepository.findById(produtoImagemId)
				.orElseThrow(() -> new ProdutoImagemNaoEncontradaException(produtoImagemId));
	}

    public ProdutoImagem inserir(Long produtoId,  MultipartFile file) {
        Produto produto = produtoRepository.findById(produtoId).get();
        ProdutoImagem produtoImagem = new ProdutoImagem();

		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(produto.getId()) + file.getOriginalFilename();
				Path caminho = Paths
						.get("c:/imagens/" +nomeImagem );
				Files.write(caminho, bytes);
                produtoImagem.setNome(nomeImagem);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

        produtoImagem.setProduto(produto);
        //objeto.setDataCriacao(new Date());
        produtoImagem = produtoImagemRepository.saveAndFlush(produtoImagem);
        return produtoImagem;
    }

	@Transactional
	public void excluir(Long produtoImagemId) {
		try {
			produtoImagemRepository.deleteById(produtoImagemId);
			produtoImagemRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoImagemNaoEncontradaException(produtoImagemId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_IMAGEM_EM_USO, produtoImagemId));
		}
	}
}
