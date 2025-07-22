package ru.fil.notificationservice.handler;

import ru.fil.common.EmailNotification;

public interface NotificationHandler {

    void handleNotification(EmailNotification notification);
}
