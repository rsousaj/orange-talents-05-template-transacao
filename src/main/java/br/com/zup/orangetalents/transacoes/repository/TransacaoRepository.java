package br.com.zup.orangetalents.transacoes.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.orangetalents.transacoes.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

	List<Transacao> findTop10ByCartaoIdOrderByDataEfetivacaoDesc(String idCartao);
}
