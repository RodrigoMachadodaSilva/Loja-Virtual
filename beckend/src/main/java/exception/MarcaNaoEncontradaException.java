package exception;

public class MarcaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public MarcaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public MarcaNaoEncontradaException(Long marcaId) {
		this(String.format("Não existe um cadastro de marca com código %d", marcaId));
	}

}
