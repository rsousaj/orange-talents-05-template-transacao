package br.com.zup.orangetalents.transacoes.model;

import java.math.BigDecimal;
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
	@AttributeOverrides({
		@AttributeOverride(name = "nome", column = @Column(name = "nome_estabelecimento")),
		@AttributeOverride(name = "cidade", column = @Column(name = "cidade_estabelecimento")),
		@AttributeOverride(name = "endereco", column = @Column(name = "endereco_estabelecimento"))
	})
	private Estabelecimento estabelecimento;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "id_cartao")),
		@AttributeOverride(name = "email", column = @Column(name = "email_cartao"))
	})
	private Cartao cartao;
	
	@Deprecated
	public Transacao() { }

	public Transacao(UUID id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao) {
		this.id = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
	}
}
