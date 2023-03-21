package com.loja.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loja.lojavirtual.entity.ProdutoImagem;
import com.loja.lojavirtual.service.ProdutoImagemService;

@RestController
@RequestMapping("/produtoImagem")
public class ProdutoImagemController {

	@Autowired
	private ProdutoImagemService produtoImagemService;

	@GetMapping
	public List<ProdutoImagem> listar() {
		return produtoImagemService.listar();
	}

	@GetMapping("/{produtoImagemId}")
	public ProdutoImagem buscarPorId(@PathVariable Long produtoImagemId) {

		return produtoImagemService.buscar(produtoImagemId);

	}

	@PostMapping()
	public ProdutoImagem inserir(@RequestParam("produtoId") Long produtoId, @RequestParam("file") MultipartFile file) {
		return produtoImagemService.inserir(produtoId, file);

	}

	@DeleteMapping("/{produtoImagemId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long produtoImagemId) {
		produtoImagemService.excluir(produtoImagemId);
	}

	/*
	 * @PutMapping("/{produtoImagemId}") public ProdutoImagem
	 * atualizar(@PathVariable Long produtoImagemId, @RequestBody ProdutoImagem
	 * produtoImagem) { ProdutoImagem produtoAtual =
	 * produtoImagemService.buscar(produtoImagemId);
	 * 
	 * BeanUtils.copyProperties(produtoImagem, produtoAtual);
	 * 
	 * return produtoImagemService.inserir(produtoAtual);
	 * 
	 * }
	 */

}
