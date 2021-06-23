package br.com.zup.orangetalents.transacoes.listener;

import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.zup.orangetalents.transacoes.repository.TransacaoRepository;

@Component
public class ProcessaTransacao {

	private final TransacaoRepository transacaoRepository;

	public ProcessaTransacao(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}

	@KafkaListener(topics = "transacoes")
	void processa(TransacaoEvento transacao) {
		UUID id = UUID.fromString(transacao.getId());
		
		transacaoRepository.findById(id)
			.orElse(transacaoRepository.save(transacao.toModel()));
	}
}
