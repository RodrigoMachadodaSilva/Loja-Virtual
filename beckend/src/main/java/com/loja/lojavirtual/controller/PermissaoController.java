package com.loja.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.lojavirtual.entity.Permissao;
import com.loja.lojavirtual.service.PermissaoService;

@RestController
@RequestMapping("/permissao")
@CrossOrigin
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;

	@GetMapping()
	public List<Permissao> buscarTodos() {
		return permissaoService.buscarTodos();
	}

	@PostMapping()
	public Permissao inserir(@RequestBody Permissao permissao) {
		return permissaoService.inserir(permissao);
	}

	@PutMapping()
	public Permissao alterar(@RequestBody Permissao permissao) {
		return permissaoService.alterar(permissao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		permissaoService.excluir(id);
		return ResponseEntity.ok().build();
	}

}
