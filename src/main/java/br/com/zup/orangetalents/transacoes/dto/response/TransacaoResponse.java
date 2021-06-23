package br.com.zup.orangetalents.transacoes.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zup.orangetalents.transacoes.model.Cartao;
import br.com.zup.orangetalents.transacoes.model.Estabelecimento;
import br.com.zup.orangetalents.transacoes.model.Transacao;

public class TransacaoResponse {

	private String id;
	private BigDecimal valor;
	private EstabelecimentoResponse estabelecimento;
	private CartaoResponse cartao;
	private LocalDateTime efetivadaEm;

	private TransacaoResponse(String id, BigDecimal valor, EstabelecimentoResponse estabelecimento,
			CartaoResponse cartao, LocalDateTime efetivadaEm) {

		this.id = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.efetivadaEm = efetivadaEm;
	}

	public static TransacaoResponse build(Transacao transacao) {
		return new TransacaoResponse(transacao.getId().toString(), transacao.getValor(),
				EstabelecimentoResponse.build(transacao.getEstabelecimento()),
				CartaoResponse.build(transacao.getCartao()), transacao.getDataEfetivacao());
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public EstabelecimentoResponse getEstabelecimento() {
		return estabelecimento;
	}

	public CartaoResponse getCartao() {
		return cartao;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

	static class EstabelecimentoResponse {

		private String nome;
		private String cidade;
		private String endereco;

		private EstabelecimentoResponse(String nome, String cidade, String endereco) {
			this.nome = nome;
			this.cidade = cidade;
			this.endereco = endereco;
		}

		public String getNome() {
			return nome;
		}

		public String getCidade() {
			return cidade;
		}

		public String getEndereco() {
			return endereco;
		}

		public static EstabelecimentoResponse build(Estabelecimento estabelecimento) {
			return new EstabelecimentoResponse(estabelecimento.getNome(), estabelecimento.getCidade(),
					estabelecimento.getCidade());
		}
	}

	static class CartaoResponse {

		private String id;
		private String email;

		private CartaoResponse(String id, String email) {
			this.id = id;
			this.email = email;
		}

		public String getId() {
			return id;
		}

		public String getEmail() {
			return email;
		}

		public static CartaoResponse build(Cartao cartao) {
			return new CartaoResponse(cartao.getId(), cartao.getEmail());
		}

	}
}
