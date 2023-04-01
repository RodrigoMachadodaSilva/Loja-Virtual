package exception;

public class CompraNaoEncontradaException extends EntidadeNaoEncontradaException {

	public CompraNaoEncontradaException(String codigoCompra) {
		super(String.format("Não exite compra com código %s cadastrada", codigoCompra));
	}

	private static final long serialVersionUID = 1L;

}
