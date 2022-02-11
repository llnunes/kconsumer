package com.live.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.live.kafka.consumer.dtos.CarDTO;

@Configuration
@EnableKafka
public class Config {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, CarDTO> kafkaListenerContainerFactory(
			ConsumerFactory<String, CarDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, CarDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, CarDTO> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerProps(), new StringDeserializer(),
				new JsonDeserializer<>(CarDTO.class, false));
	}

	private Map<String, Object> consumerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return props;
	}

//    @Bean
//    public Sender sender(KafkaTemplate<Integer, String> template) {
//        return new Sender(template);
//    }

//    @Bean
//    public Listener listener() {
//        return new Listener();
//    }

//    @Bean
//    public ProducerFactory<Integer, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(senderProps());
//    }

//    private Map<String, Object> senderProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        //...
//        return props;
//    }

//    @Bean
//    public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory<Integer, String> producerFactory) {
//        return new KafkaTemplate<Integer, String>(producerFactory);
//    }

}