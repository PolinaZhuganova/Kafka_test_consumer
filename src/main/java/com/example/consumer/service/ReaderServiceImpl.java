package com.example.consumer.service;

import com.example.consumer.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

/**
 * Класс ReaderServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReaderServiceImpl implements ReaderService {

	private final ObjectMapper mapper;
	private final KafkaConsumer<Long, String> kafkaConsumer;

	@Value("${kafka.topic}")
	private String kafkaTopic;

	@Override
	public List<MessageDto> readMessages(int count) throws IOException {
		log.info("start reading a message");
		boolean flag = true;
		int resultCount = 0;

		List<MessageDto> messagesFromKafka = new ArrayList<>();
		kafkaConsumer.subscribe(Arrays.asList(kafkaTopic));
		TopicPartition topicPartition = new TopicPartition(kafkaTopic, 0);
		while (flag) {
			ConsumerRecords<Long, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
			kafkaConsumer.seekToBeginning(Collections.singleton(topicPartition));
			int recordCount = records.count();
			for (ConsumerRecord<Long, String> record : records) {
				if (record.value() != null) {
					if (resultCount >= recordCount - count) {
						messagesFromKafka.add(mapper.readValue(record.value(), MessageDto.class));
					}
					resultCount++;
				}
			}
			if (recordCount > 0) {
				flag = false;
			}
		}
		return messagesFromKafka;
	}

}