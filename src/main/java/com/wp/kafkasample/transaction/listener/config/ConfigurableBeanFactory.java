package com.wp.kafkasample.transaction.listener.config;

import com.wp.kafkasample.transaction.listener.model.LargePackage;
import com.wp.kafkasample.transaction.listener.model.MediumPackage;
import com.wp.kafkasample.transaction.listener.model.SmallPackage;
import com.wp.kafkasample.transaction.listener.service.ListenerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.wp.kafkasample.transaction.listener.config.KafkaConfig.*;

@Component
@Slf4j
public class ConfigurableBeanFactory implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        log.info("register new bean");
        beanFactory.registerSingleton("s1", msgContainer1());
        beanFactory.registerSingleton("s2", msgContainer2());
        beanFactory.registerSingleton("s3", msgContainer3());

    }


    private <T> ConsumerFactory<String, T> consumerFactory(String groupId, Class<T> clz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(clz));
    }

    private KafkaMessageListenerContainer<String, LargePackage> msgContainer1() {

        ContainerProperties properties = new ContainerProperties(LARGE_TOPIC);
        properties.setMessageListener(new ListenerService());
        return new KafkaMessageListenerContainer<>(consumerFactory("large", LargePackage.class), properties);
    }

    private KafkaMessageListenerContainer<String, MediumPackage> msgContainer2() {

        ContainerProperties properties = new ContainerProperties(MEDIUM_TOPIC);
        properties.setMessageListener(new ListenerService());
        return new KafkaMessageListenerContainer<>(consumerFactory("medium", MediumPackage.class), properties);
    }

    private KafkaMessageListenerContainer<String, SmallPackage> msgContainer3() {

        ContainerProperties properties = new ContainerProperties(SMALL_TOPIC);
        properties.setMessageListener(new ListenerService());
        return new KafkaMessageListenerContainer<>(consumerFactory("small", SmallPackage.class), properties);
    }
}
