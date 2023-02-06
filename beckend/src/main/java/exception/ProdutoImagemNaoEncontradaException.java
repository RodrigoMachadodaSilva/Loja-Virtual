package exception;

public class ProdutoImagemNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoImagemNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public ProdutoImagemNaoEncontradaException(Long produtoImagemId) {
		this(String.format("Não existe um cadastro de imagem com código %d", produtoImagemId));
	}

}
