package ru.fil.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fil.common.EmailNotification;
import ru.fil.notificationservice.handler.NotificationDispatcher;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationDispatcher notificationDispatcher;

    @KafkaListener(topics = "${spring.kafka.consumer.notification-topic}")
    public void consume(EmailNotification notification) {
        log.info("Consumer get notification for {}", notification.getEmailTo());
        notificationDispatcher.dispatch(notification);
    }
}
