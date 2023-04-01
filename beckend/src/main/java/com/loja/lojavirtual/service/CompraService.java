package com.loja.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.lojavirtual.entity.Compra;
import com.loja.lojavirtual.entity.Pessoa;
import com.loja.lojavirtual.entity.Produto;
import com.loja.lojavirtual.repository.CompraRepository;
import com.loja.lojavirtual.repository.ProdutoRepository;

import exception.CompraNaoEncontradaException;
import exception.NegocioException;
import exception.ProdutoNaoEncontradoException;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private PessoaClienteService pessoaClienteService;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Compra> listar() {
		return compraRepository.findAll();
	}

	public Compra buscarporCodigo(String codigo) {
		return compraRepository.findByCodigo(codigo).orElseThrow(() -> new CompraNaoEncontradaException(codigo));
	}

	public List<Compra> listarComprasCliente(Long clienteId) {
		Pessoa cliente = pessoaClienteService.buscarPorId(clienteId);
		List<Compra> compra = compraRepository.findByClienteId(cliente.getId());
		return compra;
	}

	@Transactional
	public Compra registrarCompra(Compra compra) {
		validarClienteCompra(compra);
		validarItens(compra);
		compra.setTaxaFrete(compra.getTaxaFrete());
		compra.calcularValorTotal();

		return compraRepository.save(compra);

	}

	private void validarClienteCompra(Compra compra) {
		Pessoa cliente = pessoaClienteService.buscarPorId(compra.getCliente().getId());
		compra.setCliente(cliente);

	}

	private void validarItens(Compra compra) {
		compra.getItens().forEach(item -> {
			Optional<Produto> produto = produtoRepository.findById(item.getProduto().getId());
			if (produto.isEmpty()) {
				throw new ProdutoNaoEncontradoException(
						String.format("Produto de ID %s não está cadastrado", item.getProduto().getId()));

			}
			item.setCompra(compra);
			item.setProduto(produto.get());
			item.setPrecoUnitario(produto.get().getValor_Venda());

		});
	}

}
