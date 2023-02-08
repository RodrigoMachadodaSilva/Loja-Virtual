package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.repository.PessoaRepository;

import exception.PessoaNaoEncontradaException;

@Service
public class PessoaClienteService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscarPorId(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
	}

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public void excluir(Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}
}
