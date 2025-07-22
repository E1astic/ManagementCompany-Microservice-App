package ru.fil.notificationservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.fil.common.AppStatusChangeNotification;
import ru.fil.common.EmailNotification;
import ru.fil.notificationservice.service.EmailService;

@Component
@RequiredArgsConstructor
public class StatusChangeNotificationHandler implements NotificationHandler {

    private final EmailService emailService;

    @Override
    public void handleNotification(EmailNotification notification) {
        AppStatusChangeNotification appStatusChangeNotification = (AppStatusChangeNotification) notification;
        emailService.sendStatusChangeEmail(appStatusChangeNotification.getEmailTo(),
                appStatusChangeNotification.getApplicationId(),
                appStatusChangeNotification.getApplicationStatus());
    }
}
