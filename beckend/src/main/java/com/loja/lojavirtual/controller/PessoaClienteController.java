
package com.loja.lojavirtual.controller;

import java.util.List;
import java.util.Optional;

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

import com.loja.lojavirtual.assembler.PessoaClienteInputDisassembler;
import com.loja.lojavirtual.assembler.PessoaClienteModelAssembler;
import com.loja.lojavirtual.dto.input.PessoaClienteInput;
import com.loja.lojavirtual.dto.model.PessoaClienteModel;
import com.loja.lojavirtual.entity.Permissao;
import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.repository.PermissaoRepository;
import com.loja.lojavirtual.service.PessoaClienteService;

@RestController

@RequestMapping("/pessoaCliente")
public class PessoaClienteController {

	@Autowired
	private PessoaClienteService pessoaClienteService;

	@Autowired
	private PessoaClienteModelAssembler pessoaClienteModelAssembler;

	@Autowired
	public PessoaClienteInputDisassembler pessoaClienteInputDisassembler;
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@GetMapping
	public List<PessoaClienteModel> listar() {

		List<Pessoa> pessoas = pessoaClienteService.listar();

		return pessoaClienteModelAssembler.toCollectionModel(pessoas);
	}

	@GetMapping("/{pessoaId}")
	public PessoaClienteModel buscarPorId(@PathVariable Long pessoaId) {

		Pessoa pessoa = pessoaClienteService.buscarPorId(pessoaId);

		return pessoaClienteModelAssembler.toModel(pessoa);
	}

	@PostMapping

	@ResponseStatus(HttpStatus.CREATED)
	public PessoaClienteModel salvar(@RequestBody PessoaClienteInput pessoaInput) {
		Pessoa pessoa = pessoaClienteInputDisassembler.toDomainObject(pessoaInput);
		

		Pessoa pessoaSalvar = pessoaClienteService.salvar(pessoa);

		return pessoaClienteModelAssembler.toModel(pessoaSalvar);
	}

	@DeleteMapping("/{pessoaId}")

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long pessoaId) {
		pessoaClienteService.excluir(pessoaId);
	}

	@PutMapping("/{pessoaId}")
	public PessoaClienteModel atualizar(@PathVariable Long pessoaId, @RequestBody PessoaClienteInput pessoaInput) {
		Pessoa pessoaAtual = pessoaClienteService.buscarPorId(pessoaId);

		pessoaClienteInputDisassembler.copyToDomainObject(pessoaInput, pessoaAtual);

		pessoaAtual = pessoaClienteService.salvar(pessoaAtual);

		return pessoaClienteModelAssembler.toModel(pessoaAtual);

	}

}
