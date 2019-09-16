package com.wp.kafkasample.transaction.listener.config;

import com.wp.kafkasample.transaction.listener.model.DeliveryPackage;
import com.wp.kafkasample.transaction.listener.model.LargePackage;
import com.wp.kafkasample.transaction.listener.model.MediumPackage;
import com.wp.kafkasample.transaction.listener.model.SmallPackage;
import com.wp.kafkasample.transaction.listener.service.ListenerService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
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
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {


    @Autowired
    private KafkaProperties kafkaProperties;

    public static String LARGE_TOPIC = "large";
    public static String MEDIUM_TOPIC = "medium";
    public static String SMALL_TOPIC = "small";


    public ProducerFactory<String, DeliveryPackage> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new DefaultKafkaProducerFactory<>(configProps);
    }


    @Bean
    public KafkaTemplate<String, DeliveryPackage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


//    private ConsumerFactory<String, LargePackage> largePackageConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "large");
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "large");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(LargePackage.class));
//    }
//
//    private ConsumerFactory<String, MediumPackage> mediumPackageConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "medium");
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "medium");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(MediumPackage.class));
//    }
//
//    private ConsumerFactory<String, SmallPackage> smallPackageConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "small");
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "small");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(SmallPackage.class));
//    }

//    @Bean
//    public KafkaMessageListenerContainer<String, LargePackage> msgContainer1() {
//
//        ContainerProperties properties = new ContainerProperties(LARGE_TOPIC);
//        properties.setMessageListener(new ListenerService());
//        return new KafkaMessageListenerContainer<>(largePackageConsumerFactory(), properties);
//    }
//
//    @Bean
//    public KafkaMessageListenerContainer<String, MediumPackage> msgContainer2() {
//
//        ContainerProperties properties = new ContainerProperties(MEDIUM_TOPIC);
//        properties.setMessageListener(new ListenerService());
//        return new KafkaMessageListenerContainer<>(mediumPackageConsumerFactory(), properties);
//    }
//
//    @Bean
//    public KafkaMessageListenerContainer<String, SmallPackage> msgContainer3() {
//
//        ContainerProperties properties = new ContainerProperties(SMALL_TOPIC);
//        properties.setMessageListener(new ListenerService());
//        return new KafkaMessageListenerContainer<>(smallPackageConsumerFactory(), properties);
//    }
}
