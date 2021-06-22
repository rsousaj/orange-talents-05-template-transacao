package br.com.zup.orangetalents.transacoes.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import br.com.zup.orangetalents.transacoes.model.Cartao;
import br.com.zup.orangetalents.transacoes.model.Estabelecimento;
import br.com.zup.orangetalents.transacoes.model.Transacao;

public class TransacaoEvento {

	private String id;
	private BigDecimal valor;
	private EstabelecimentoEveto estabelecimento;
	private CartaoEvento cartao;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime efetivadaEm;

	public TransacaoEvento() {
		
	}
	
	public TransacaoEvento(String id, BigDecimal valor) {
		this.id = id;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public EstabelecimentoEveto getEstabelecimento() {
		return this.estabelecimento;
	}
	
	public CartaoEvento getCartao() {
		return this.cartao;
	}

	@Override
	public String toString() {
		return "TransacaoResponse [id=" + id + ", valor=" + valor + ", estabelecimento=" + estabelecimento + ", cartao="
				+ cartao + ", efetivadaEm=" + efetivadaEm + "]";
	}
	
	public Transacao toModel() {
		Cartao cartao = new Cartao(this.getCartao().getId(), this.getCartao().getEmail());
		Estabelecimento estabelecimento = new Estabelecimento(this.getEstabelecimento().getNome(), 
				this.getEstabelecimento().getCidade(), 
				this.getEstabelecimento().getEndereco());
		
		return new Transacao(UUID.fromString(this.id), valor, estabelecimento, cartao);
	}
	
	private static class EstabelecimentoEveto {
		private String nome;
		private String cidade;
		private String endereco;
		
		public EstabelecimentoEveto() { }

		public EstabelecimentoEveto(String nome, String cidade, String endereco) {
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

		@Override
		public String toString() {
			return "EstabelecimentoResponse [nome=" + nome + ", cidade=" + cidade + ", endereco=" + endereco + "]";
		}
	}

	private static class CartaoEvento {
		private String id;
		private String email;
		
		public CartaoEvento() { }

		public CartaoEvento(String id, String email) {
			this.id = id;
			this.email = email;
		}

		public String getId() {
			return id;
		}

		public String getEmail() {
			return email;
		}

		@Override
		public String toString() {
			return "CartaoResponse [id=" + id + ", email=" + email + "]";
		}
	}
}