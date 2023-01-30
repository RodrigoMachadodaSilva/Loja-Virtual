package exception;

public class MarcaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MarcaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public MarcaNaoEncontradaException(Long marcaId) {
		this(String.format("Não existe um cadastro de estado com código %d", marcaId));
	}

}
