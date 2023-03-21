package com.loja.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.lojavirtual.assembler.CompraInputDisassembler;
import com.loja.lojavirtual.assembler.CompraModelAssembler;
import com.loja.lojavirtual.dto.input.CompraInput;
import com.loja.lojavirtual.dto.model.CompraModel;
import com.loja.lojavirtual.entity.Compra;
import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.service.CompraService;
import com.loja.lojavirtual.service.PessoaClienteService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	private CompraService compraService;

	@Autowired
	private PessoaClienteService pessoaClienteService;

	@Autowired
	private CompraModelAssembler compraModelAssembler;

	@Autowired
	private CompraInputDisassembler compraInputDisassembler;

	@GetMapping
	public List<CompraModel> listar() {
		List<Compra> compra = compraService.listar();
		return compraModelAssembler.toCollectionModel(compra);
	}

	@GetMapping("/cliente/{compraId}")
	public List<CompraModel> buscar(@PathVariable Long compraId) {
		List<Compra> compra = compraService.listarComprasCliente(compraId);

		return compraModelAssembler.toCollectionModel(compra);

	}

	@GetMapping("/{clienteId}")
	public CompraModel buscarCompraId(@PathVariable Long clienteId) {
		Compra compra = compraService.buscarporId(clienteId);

		return compraModelAssembler.toModel(compra);
	}

	@PostMapping
	public CompraModel salvar(@RequestBody CompraInput compraInput) {

		Compra compra = compraInputDisassembler.toDomainObject(compraInput);
		
		compra.setCliente(new Pessoa());
		compra.getCliente().setId(2L);

		Compra compraSalva = compraService.registrarCompra(compra);

		return compraModelAssembler.toModel(compraSalva);

	}

}
