package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.loja.lojavirtual.entity.Categoria;
import com.loja.lojavirtual.repository.CategoriaRepository;

import exception.CategoriaNaoEncontradaException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar () {
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarOuFalhar( Long  categoriaId) {
		return categoriaRepository.findById(categoriaId).orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));
	}
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void excluir(Long categoriaId) {
		categoriaRepository.deleteById(categoriaId);
	}
	
	

}
