package com.loja.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Estado;
import com.loja.lojavirtual.repository.EstadoRepository;

import exception.EstadoNaoEncontradoException;
import exception.NegocioException;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public Estado salvar(Estado estado) {
		validarEstadoDuplicado(estado);
		return estadoRepository.save(estado);
	}

	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);

		} catch (DataIntegrityViolationException e) {
			throw new exception.EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
	
	


	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}
	
	public void validarEstadoDuplicado( Estado estado) {
		Estado estadoExistente = estadoRepository.findByNome(estado.getNome());
		if(estadoExistente != null && estadoExistente.getId() != estado.getId()) {
			throw new NegocioException(String.format("O estado de nome %s já está cadastrado", estado.getNome()));
		}
	}

}
