package ru.fil.applicationservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fil.common.dto.NotificationBody;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    @Value("${spring.kafka.producer.notification-topic}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(NotificationBody notification) {
        log.info("Producer send notification for {}", notification.getEmailTo());
        kafkaTemplate.send(topic, notification);
    }
}
