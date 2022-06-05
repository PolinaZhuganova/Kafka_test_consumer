package com.example.consumer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.*;

import java.util.*;

/**
 * Класс ConsumerConfig
 */
@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {

	@Value("${kafka.server}")
	private String kafkaServer;

	@Value("${kafka.group.id}")
	private String kafkaGroupId;

	@Bean
	public KafkaListenerContainerFactory<?> batchFactory() {
		ConcurrentKafkaListenerContainerFactory<Long, String> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setBatchListener(true);
		factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
		return factory;
	}

	@Bean
	public KafkaListenerContainerFactory<?> singleFactory() {
		ConcurrentKafkaListenerContainerFactory<Long, String> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setBatchListener(false);
		factory.setMessageConverter(new StringJsonMessageConverter());
		return factory;
	}

	@Bean
	public ConsumerFactory<Long, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "1048576000");
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

		return props;
	}

	@Bean
	public KafkaConsumer<Long, String> kafkaConsumer() {
		KafkaConsumer<Long, String> template = new KafkaConsumer<>(consumerConfigs());
		return template;
	}

	@Bean
	public StringJsonMessageConverter converter() {
		return new StringJsonMessageConverter();
	}
}