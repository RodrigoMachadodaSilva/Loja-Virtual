package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.repository.CategoriaRepository;

import exception.CategoriaNaoEncontradaException;
import exception.EntidadeEmUsoException;
import exception.NegocioException;

@Service
public class CategoriaService {
	
	private static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removidoa, pois está em uso";
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar () {
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarOuFalhar( Long  categoriaId) {
		return categoriaRepository.findById(categoriaId).orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));
	}
	
	public Categoria salvar(Categoria categoria) {
		validarCategoriaExistente(categoria);
		return categoriaRepository.save(categoria);
	}
	
	public void excluir(Long categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);
			categoriaRepository.flush();
			
		}catch(EmptyResultDataAccessException e) {
			throw new CategoriaNaoEncontradaException(categoriaId);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CATEGORIA_EM_USO, categoriaId));
		}
		

	}
	
	public void validarCategoriaExistente(Categoria categoria) {
		Categoria categoriaExistente = categoriaRepository.findByNome(categoria.getNome());
		if(categoriaExistente != null && categoriaExistente.getId() != categoria.getId()) {
			throw new NegocioException(String.format("Já existe categoria de nome %s cadastrada", categoria.getNome()));
			
		}
	}
	
	
}

