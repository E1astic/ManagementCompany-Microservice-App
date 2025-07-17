package ru.fil.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fil.common.dto.NotificationBody;
import ru.fil.notificationservice.service.EmailService;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.consumer.notification-topic}")
    public void consume(NotificationBody notification) {
        log.info("Consumer get notification for {}", notification.getEmailTo());
        emailService.sendStatusEmail(notification);
    }
}
