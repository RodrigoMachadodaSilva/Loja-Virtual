package com.loja.lojavirtual.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Permissao;
import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.repository.PermissaoRepository;
import com.loja.lojavirtual.repository.PessoaRepository;

import exception.EntidadeNaoEncontradaException;
import exception.PessoaNaoEncontradaException;

@Service
public class PessoaClienteService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscarPorId(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
	}

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		vincularPessoaPermissao(pessoa);
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public void excluir(Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}
	
	public void vincularPessoaPermissao(Pessoa pessoa) {
		Set<Permissao> listaPermissao = permissaoRepository.findByNome("cliente");
		if(listaPermissao.size()>0) {
			pessoa.setPermissoes(listaPermissao);
		}
	}
	
}
