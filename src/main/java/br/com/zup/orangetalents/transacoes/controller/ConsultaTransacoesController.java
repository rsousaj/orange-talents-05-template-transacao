package br.com.zup.orangetalents.transacoes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orangetalents.transacoes.dto.response.TransacaoResponse;
import br.com.zup.orangetalents.transacoes.exception.ErroResponse;
import br.com.zup.orangetalents.transacoes.model.Transacao;
import br.com.zup.orangetalents.transacoes.repository.TransacaoRepository;

@RestController
public class ConsultaTransacoesController {

	private final TransacaoRepository transacaoRepository;
	
	public ConsultaTransacoesController(TransacaoRepository transacaoRepository) {	
		this.transacaoRepository = transacaoRepository;
	}

	@GetMapping("${transacoes.consulta.uri}")
	ResponseEntity<?> consulta(@PathVariable String idCartao) {
		List<Transacao> transacoes = transacaoRepository.findTop10ByCartaoIdOrderByDataEfetivacaoDesc(idCartao);
			
		if (transacoes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErroResponse.build("Cartão não encontrado"));
		}
		
		return ResponseEntity.ok(transacoes
					.stream()
					.map(TransacaoResponse::build)
					.collect(Collectors.toList())
				);
	}
}
