package com.loja.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loja.lojavirtual.assembler.ProdutoInputDisassembler;
import com.loja.lojavirtual.assembler.ProdutoModelAssembler;
import com.loja.lojavirtual.dto.input.ProdutoInput;
import com.loja.lojavirtual.dto.model.ProdutoModel;
import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.service.ProdutoService;

@RestController
@RequestMapping("categoria{categoriaId}/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	public ProdutoInputDisassembler produtoInputDisassembler;

	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long categoriaId) {

		List<Produto> produtos = produtoService.listar(categoriaId);

		return produtoModelAssembler.toCollectionModel(produtos);
	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscarPorId(@PathVariable Long produtoId, @PathVariable Long categoriaId) {

		Produto produto = produtoService.buscar(produtoId, categoriaId);

		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@PathVariable Long categoriaId, @RequestBody ProdutoInput produtoInput) {
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);

		Produto produtosalvar = produtoService.salvar(categoriaId, produto);

		return produtoModelAssembler.toModel(produtosalvar);
	}

	@DeleteMapping("/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir( @PathVariable Long categoriaId, @PathVariable Long produtoId) {
		produtoService.excluir(categoriaId, produtoId);
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long produtoId, @PathVariable Long categoriaId,
			@RequestBody ProdutoInput produtoInput) {
		Produto produtoAtual = produtoService.buscar(produtoId, categoriaId);

		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

		produtoAtual = produtoService.salvar(categoriaId, produtoAtual);

		return produtoModelAssembler.toModel(produtoAtual);

	}

}
