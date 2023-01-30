package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Marca;
import com.loja.lojavirtual.repository.MarcaRepository;

import exception.MarcaNaoEncontradaException;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;

	public List<Marca> listar() {
		return marcaRepository.findAll();

	}

	public Marca buscarporId(Long marcaId) {
		return marcaRepository.findById(marcaId).orElseThrow(() -> new MarcaNaoEncontradaException(marcaId));
	}

	public Marca salvar(Marca marca) {
		return marcaRepository.save(marca);
	}

	@Transactional
	public void excluir(Long marcaId) {
		try {
			marcaRepository.deleteById(marcaId);
			marcaRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new MarcaNaoEncontradaException(marcaId);

		}
	}

}
