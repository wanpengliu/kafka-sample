package com.wp.kafkasample.transaction.listener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Slf4j
public class ListenerService implements MessageListener<String, Package> {

    @Override
    public void onMessage(ConsumerRecord<String, Package> consumerRecord) {

        log.info("receive message with key={} and detail={} from topic={}",
                consumerRecord.key(), consumerRecord.value(), consumerRecord.topic());
    }
}
