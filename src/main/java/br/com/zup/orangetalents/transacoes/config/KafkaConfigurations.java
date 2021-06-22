package br.com.zup.orangetalents.transacoes.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.zup.orangetalents.transacoes.dto.response.TransacaoEvento;

@Configuration
@EnableKafka
public class KafkaConfigurations {

	private final KafkaProperties kafkaProperties;
	
	public KafkaConfigurations(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}
	
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, TransacaoEvento> kafkaListenerContainerFactory(
			ConsumerFactory<String, TransacaoEvento> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, TransacaoEvento> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);

		return factory;
	}

	@Bean
	ConsumerFactory<String, TransacaoEvento> consumerFactory() {
		StringDeserializer stringDeserializer = new StringDeserializer();
	    JsonDeserializer<TransacaoEvento> jsonDeserializer = new JsonDeserializer<>(TransacaoEvento.class, false);
		return new DefaultKafkaConsumerFactory<>(consumerProps(), stringDeserializer, jsonDeserializer);
	}

	private Map<String, Object> consumerProps() {
		Map<String, Object> props = new HashMap<>();
		var consumer = kafkaProperties.getConsumer();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, consumer.getGroupId());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumer.getKeyDeserializer());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumer.getValueDeserializer());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumer.getAutoOffsetReset());

		return props;
	}
}
