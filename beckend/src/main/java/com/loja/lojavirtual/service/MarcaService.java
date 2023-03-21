package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.lojavirtual.entity.Marca;
import com.loja.lojavirtual.repository.MarcaRepository;

import exception.EntidadeEmUsoException;
import exception.MarcaNaoEncontradaException;
import exception.NegocioException;

@Service
public class MarcaService {
	
	private static final String MSG_MARCA_EM_USO = "Marca de código %d não pode ser removido, pois está em uso";
	
	
	@Autowired
	private MarcaRepository marcaRepository;

	public List<Marca> listar() {
		return marcaRepository.findAll();

	}

	public Marca buscarporId(Long marcaId) {
		return marcaRepository.findById(marcaId).orElseThrow(() -> new MarcaNaoEncontradaException(marcaId));
	}

	public Marca salvar(Marca marca) {
		validarMarcaDuplicada(marca);
		return marcaRepository.save(marca);
	}


	@Transactional
	public void excluir(Long marcaId) {
		try {
			marcaRepository.deleteById(marcaId);
			marcaRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new MarcaNaoEncontradaException(marcaId);

		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_MARCA_EM_USO, marcaId));
		}
	}
	
	private void validarMarcaDuplicada(Marca marca) {
		Marca marcaExistente = marcaRepository.findByNome(marca.getNome());
		if(marcaExistente != null && marcaExistente.getId() != marca.getId() ) {
			throw new NegocioException(String.format("Já existe marca de nome %s salva no cadastro",
					marca.getNome().toUpperCase()));
		}
		
	}


}
