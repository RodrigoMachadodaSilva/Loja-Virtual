package com.loja.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loja.lojavirtual.entity.Compra;

import com.loja.lojavirtual.repository.CompraRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	public List<Compra> listar() {
		return  compraRepository.findAll();
	}
	

	

	

}
