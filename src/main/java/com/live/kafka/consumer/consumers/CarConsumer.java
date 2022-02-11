package com.live.kafka.consumer.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.live.kafka.consumer.dtos.CarDTO;

@Component
public class CarConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(CarConsumer.class);
	
	@Value("${topic.name}")
	private String topic;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;	
	
	@KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
	public void listenTopicCar(ConsumerRecord<String, CarDTO> record) {
		log.info("Received Message - Partition: " + record.partition());
		log.info("Received Message - Value: " + record.value());
	}
	
	
}
