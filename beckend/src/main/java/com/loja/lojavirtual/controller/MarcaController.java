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

import com.loja.lojavirtual.assembler.MarcaInputDisassembler;
import com.loja.lojavirtual.assembler.MarcaModelAssembler;
import com.loja.lojavirtual.dto.input.MarcaInput;
import com.loja.lojavirtual.dto.model.MarcaModel;
import com.loja.lojavirtual.entity.Marca;
import com.loja.lojavirtual.service.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private MarcaModelAssembler marcaModelAssembler;

	@Autowired
	private MarcaInputDisassembler marcaInputDisassembler;

	@GetMapping
	public List<MarcaModel> listar() {
		List<Marca> todasMarcas = marcaService.listar();

		return marcaModelAssembler.toCollectionModel(todasMarcas);
	}

	@GetMapping("/{marcaId}")
	public MarcaModel buscar(@PathVariable Long marcaId) {
		Marca marca = marcaService.buscarporId(marcaId);

		return marcaModelAssembler.toModel(marca);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MarcaModel salvar(@RequestBody MarcaInput marcaInput) {
		Marca marca = marcaInputDisassembler.toDomainObject(marcaInput);

		Marca marcaSalva = marcaService.salvar(marca);

		return marcaModelAssembler.toModel(marcaSalva);
		
	

	}
	
	@PutMapping("/{marcaId}")
	public MarcaModel atualizar(@PathVariable Long marcaId, @RequestBody MarcaInput marcaInput) {
		 Marca marcaAtual = marcaService.buscarporId(marcaId);
		 
		 marcaInputDisassembler.copyToDomainObject(marcaInput, marcaAtual);
		 
		 marcaAtual = marcaService.salvar(marcaAtual);
		 
		 return marcaModelAssembler.toModel(marcaAtual);
		 
	}
	
	@DeleteMapping("/{marcaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long marcaId) {
		marcaService.excluir(marcaId);
	}

}
