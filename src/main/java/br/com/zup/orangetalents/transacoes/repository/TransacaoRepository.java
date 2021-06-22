package br.com.zup.orangetalents.transacoes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.orangetalents.transacoes.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

}
