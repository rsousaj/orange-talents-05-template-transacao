package br.com.zup.orangetalents.transacoes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Positive;

@Entity
public class Transacao {

	@Id
	private UUID id;

	@Positive
	private BigDecimal valor;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "nome", column = @Column(name = "nome_estabelecimento")),
			@AttributeOverride(name = "cidade", column = @Column(name = "cidade_estabelecimento")),
			@AttributeOverride(name = "endereco", column = @Column(name = "endereco_estabelecimento")) })
	private Estabelecimento estabelecimento;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id_cartao")),
			@AttributeOverride(name = "email", column = @Column(name = "email_cartao")) })
	private Cartao cartao;

	private LocalDateTime dataEfetivacao;

	@Deprecated
	public Transacao() {
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getDataEfetivacao() {
		return dataEfetivacao;
	}

	public Transacao(UUID id, @Positive BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao,
			LocalDateTime efetivadaEm) {
		this.id = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.dataEfetivacao = efetivadaEm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
