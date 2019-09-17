package com.wp.kafkasample.transaction.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListener;

@Slf4j
@AllArgsConstructor
public class TransactionListenerContainer implements MessageListener<String, String> {

    private KafkaTemplate<String, String> transactionTemplate;

    private String topic;

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {

        log.info("received consumer record with key={} and value={}", consumerRecord.key(), consumerRecord.value());

        if (consumerRecord.value().equalsIgnoreCase("fail") && topic.equalsIgnoreCase("topic-3")) {
            throw new RuntimeException("failed");
        }

        transactionTemplate.executeInTransaction(t -> t.send(topic, consumerRecord.key(), consumerRecord.value()));
    }
}
