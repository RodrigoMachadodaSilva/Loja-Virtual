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

import com.loja.lojavirtual.assembler.PessoaInputDisassembler;
import com.loja.lojavirtual.assembler.PessoaModelAssembler;
import com.loja.lojavirtual.dto.input.PessoaInput;
import com.loja.lojavirtual.dto.model.PessoaModel;
import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaModelAssembler pessoaModelAssembler;

	@Autowired
	public PessoaInputDisassembler pessoaInputDisassembler;

	@GetMapping
	public List<PessoaModel> listar() {

		List<Pessoa> pessoas = pessoaService.listar();

		return pessoaModelAssembler.toCollectionModel(pessoas);
	}

	@GetMapping("/{pessoaId}")
	public PessoaModel buscarPorId(@PathVariable Long pessoaId) {

		Pessoa pessoa = pessoaService.buscarPorId(pessoaId);

		return pessoaModelAssembler.toModel(pessoa);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaModel salvar(@RequestBody PessoaInput pessoaInput) {
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);

		Pessoa pessoaSalvar = pessoaService.salvar(pessoa);

		return pessoaModelAssembler.toModel(pessoaSalvar);
	}

	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long pessoaId) {
		pessoaService.excluir(pessoaId);
	}

	@PutMapping("/{pessoaId}")
	public PessoaModel atualizar(@PathVariable Long pessoaId, @RequestBody PessoaInput pessoaInput) {
		Pessoa pessoaAtual = pessoaService.buscarPorId(pessoaId);

		pessoaInputDisassembler.copyToDomainObject(pessoaInput, pessoaAtual);

		pessoaAtual = pessoaService.salvar(pessoaAtual);

		return pessoaModelAssembler.toModel(pessoaAtual);

	}

}
