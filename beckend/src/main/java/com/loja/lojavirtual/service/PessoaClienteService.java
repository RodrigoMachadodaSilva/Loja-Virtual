package com.loja.lojavirtual.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Permissao;
import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.repository.PermissaoRepository;
import com.loja.lojavirtual.repository.PessoaRepository;

import exception.PessoaNaoEncontradaException;

@Service
public class PessoaClienteService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private EmailService emailService;



	public Pessoa buscarPorId(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
	}

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		vincularPessoaPermissao(pessoa);
        Map<String, Object> proprMap = new HashMap<>();
        proprMap.put("nome", pessoa.getNome());
        proprMap.put("mensagem", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        emailService.enviarEmailTemplate(pessoa.getEmail(), "Cadastro na Loja", proprMap);
        return pessoa;

	}

	@Transactional
	public void excluir(Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}

	public void vincularPessoaPermissao(Pessoa pessoa) {
		Set<Permissao> listaPermissao = permissaoRepository.findByNome("cliente");
		if (listaPermissao.size() > 0) {
			//pessoa.setPermissoes(listaPermissao);
		}
	}

}
