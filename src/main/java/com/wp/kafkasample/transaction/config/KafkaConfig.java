package com.wp.kafkasample.transaction.config;

import com.wp.kafkasample.transaction.service.TransactionListenerContainer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;


    private ProducerFactory<String, String> producerFactoryNoTxn() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> noTxnkafkaTemplate() {

        return new KafkaTemplate<>(producerFactoryNoTxn());
    }


    private ProducerFactory<String, String> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "kafka-template-tx.");
        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(configProps);
        producerFactory.setTransactionIdPrefix("kafka-template-tx.");
        return producerFactory;
    }

    private KafkaTemplate<String, String> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }


    private ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> listenerContainer1(KafkaTransactionManager<Object, Object> transactionManager) {

        ContainerProperties properties = new ContainerProperties("topic-1");
        properties.setMessageListener(new TransactionListenerContainer(kafkaTemplate(), "topic-2"));
        properties.setTransactionManager(transactionManager);

        return new KafkaMessageListenerContainer<>(consumerFactory(), properties);
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> listenerContainer2(KafkaTransactionManager<Object, Object> transactionManager) {

        ContainerProperties properties = new ContainerProperties("topic-2");
        properties.setMessageListener(new TransactionListenerContainer(kafkaTemplate(), "topic-3"));
        properties.setTransactionManager(transactionManager);

        KafkaMessageListenerContainer<String, String> listenerContainer = new KafkaMessageListenerContainer<>(consumerFactory(), properties);
        listenerContainer.setErrorHandler(new SeekToCurrentErrorHandler( (cr, ex) -> {
            log.error("error happened for consumer record={}", cr.value(), ex);
        }, 3));
        return listenerContainer;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> listenerContainer3() {

        ContainerProperties properties = new ContainerProperties("topic-3");
        properties.setMessageListener(new MessageListener<String, String>() {
            @Override
            public void onMessage(ConsumerRecord<String, String> consumerRecord) {
                log.info("received message from topic-3 with value={}", consumerRecord.value());
            }
        });

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group-3");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-group-3");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        new DefaultKafkaConsumerFactory<>(props);

        return new KafkaMessageListenerContainer<>(new DefaultKafkaConsumerFactory<>(props), properties);
    }


}
