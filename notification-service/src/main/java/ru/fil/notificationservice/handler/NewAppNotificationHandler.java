package ru.fil.notificationservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.fil.common.AppCreationNotification;
import ru.fil.common.EmailNotification;
import ru.fil.notificationservice.service.EmailService;

@Component
@RequiredArgsConstructor
public class NewAppNotificationHandler implements NotificationHandler {

    private final EmailService emailService;

    @Override
    public void handleNotification(EmailNotification notification) {
        AppCreationNotification appCreationNotification = (AppCreationNotification) notification;
        emailService.sendNewAppEmailToAdmin(
                appCreationNotification.getEmailTo(), appCreationNotification.getApplicationId());
    }
}
