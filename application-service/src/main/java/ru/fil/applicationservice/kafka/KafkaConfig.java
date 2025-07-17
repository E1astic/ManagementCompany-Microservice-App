package ru.fil.applicationservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.notification-topic}")
    private String topic;

    @Value("${spring.kafka.producer.partitions-num}")
    private int partitionsNum;

    @Value("${spring.kafka.producer.replicas-num}")
    private int replicasNum;

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder
                .name(topic)
                .partitions(partitionsNum)
                .replicas(replicasNum)
                .build();
    }
}
