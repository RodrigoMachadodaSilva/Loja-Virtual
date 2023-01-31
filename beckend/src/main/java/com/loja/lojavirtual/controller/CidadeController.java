package com.loja.lojavirtual.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.loja.lojavirtual.assembler.CidadeInputDisassembler;
import com.loja.lojavirtual.assembler.CidadeModelAssembler;
import com.loja.lojavirtual.dto.input.CidadeInput;
import com.loja.lojavirtual.dto.model.CidadeModel;
import com.loja.lojavirtual.entity.Cidade;
import com.loja.lojavirtual.service.CidadeService;

import exception.EstadoNaoEncontradoException;
import exception.NegocioException;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@GetMapping
	public List<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeService.listar();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = cidadeService.salvar(cidade);
			
			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			cidadeAtual = cidadeService.salvar(cidadeAtual);
			
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);	
	}
		

}
