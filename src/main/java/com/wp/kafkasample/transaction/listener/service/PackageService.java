package com.wp.kafkasample.transaction.listener.service;

import com.wp.kafkasample.transaction.listener.model.DeliveryPackage;
import com.wp.kafkasample.transaction.listener.model.LargePackage;
import com.wp.kafkasample.transaction.listener.model.MediumPackage;
import com.wp.kafkasample.transaction.listener.model.SmallPackage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.wp.kafkasample.transaction.listener.config.KafkaConfig.*;

@Component
@AllArgsConstructor
@Slf4j
public class PackageService {

    private final KafkaTemplate<String, DeliveryPackage> kafkaTemplate;

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public void sendPackage(String message) {

        LargePackage largePackage = new LargePackage(message, 2.5, "van");

        log.info("sending message to large topic");
        atomicInteger.addAndGet(1);
        kafkaTemplate.send(LARGE_TOPIC, String.valueOf(atomicInteger.get()), largePackage);

        log.info("sending message to medium topic");
        MediumPackage mediumPackage = new MediumPackage(message, 2.0, 5.5);
        atomicInteger.addAndGet(1);
        kafkaTemplate.send(MEDIUM_TOPIC, String.valueOf(atomicInteger.get()), mediumPackage);

        log.info("sending message to small topic");
        SmallPackage smallPackage = new SmallPackage(message, 1.0, true);
        atomicInteger.addAndGet(1);
        kafkaTemplate.send(SMALL_TOPIC, String.valueOf(atomicInteger.get()), smallPackage);

    }

}
