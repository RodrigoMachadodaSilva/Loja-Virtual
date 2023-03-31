package exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long produtoId, Long categoriaId) {
		this(String.format("Não existe um cadastro de produto com código %d para  a categoria %d", produtoId , categoriaId));
	}
	
	public ProdutoNaoEncontradoException(Long produtoId) {
		this(String.format("Não existe um cadastro de produto com código %d para  a categoria %d", produtoId));
	}


}
