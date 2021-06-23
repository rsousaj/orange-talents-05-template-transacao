package br.com.zup.orangetalents.transacoes.exception;

public class ErroResponse {

	private String mensagem;

	private ErroResponse(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public static ErroResponse build(String mensagem) {
		return new ErroResponse(mensagem);
	}
}
