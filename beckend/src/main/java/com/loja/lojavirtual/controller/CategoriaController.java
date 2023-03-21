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

import com.loja.lojavirtual.assembler.CategoriaInputDisassembler;
import com.loja.lojavirtual.assembler.CategoriaModelAssembler;
import com.loja.lojavirtual.dto.input.CategoriaInput;
import com.loja.lojavirtual.dto.model.CategoriaModel;
import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaModelAssembler categoriaModelAssembler;

	@Autowired
	private CategoriaInputDisassembler categoriaInputDisassembler;

	@GetMapping
	public List<CategoriaModel> listar() {
		List<Categoria> todasCategorias = categoriaService.listar();

		return categoriaModelAssembler.toCollectionModel(todasCategorias);
	}

	@GetMapping("/{categoriaId}")
	public CategoriaModel buscar(@PathVariable Long categoriaId) {
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaId);

		return categoriaModelAssembler.toModel(categoria);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaModel adicionar(@RequestBody CategoriaInput categoriaInput) {
		Categoria categoria = categoriaInputDisassembler.toDomainObject(categoriaInput);

		categoria = categoriaService.salvar(categoria);

		return categoriaModelAssembler.toModel(categoria);
	}

	@PutMapping("/{categoriaId}")
	public CategoriaModel atualizar(@PathVariable Long categoriaId, @RequestBody CategoriaInput categoriaInput) {
		Categoria categoriaAtual = categoriaService.buscarOuFalhar(categoriaId);

		categoriaInputDisassembler.copyToDomainObject(categoriaInput, categoriaAtual);

		categoriaAtual = categoriaService.salvar(categoriaAtual);

		return categoriaModelAssembler.toModel(categoriaAtual);
	}

	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long categoriaId) {
		categoriaService.excluir(categoriaId);
	}

}
