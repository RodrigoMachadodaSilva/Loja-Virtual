package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.repository.PessoaRepository;

import exception.EntidadeEmUsoException;
import exception.NegocioException;
import exception.PessoaNaoEncontradaException;

@Service
public class PessoaService {

	private static final String MSG_PESSOA_EM_USO = "Pessoa não pode ser removida";
	
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
		validarPessoa(pessoa);
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public void excluir(Long pessoaId) {
		try {
			pessoaRepository.deleteById(pessoaId);
			pessoaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(pessoaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PESSOA_EM_USO, pessoaId));
		}
	}
			


	private void validarPessoa(Pessoa pessoa) {
		Pessoa pessoaExiste = pessoaRepository.findByCpf(pessoa.getCpf());
		if (pessoaExiste != null && pessoaExiste.getId() != pessoa.getId()) {
			throw new NegocioException(String.format("Já existe cadastro de CPF %s em nosso sistema", pessoa.getCpf()));
		}

	}
}
