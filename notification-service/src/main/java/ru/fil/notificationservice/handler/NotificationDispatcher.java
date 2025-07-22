package ru.fil.notificationservice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fil.common.EmailNotification;
import ru.fil.common.enums.NotificationType;

import java.util.Map;

@Component
public class NotificationDispatcher {

    private final Map<NotificationType, NotificationHandler> handlers;

    @Autowired
    public NotificationDispatcher(StatusChangeNotificationHandler statusChangeNotificationHandler,
                                  NewAppNotificationHandler newAppNotificationHandler) {
        handlers = Map.of(
                NotificationType.STATUS_CHANGE, statusChangeNotificationHandler,
                NotificationType.CREATION, newAppNotificationHandler);
    }

    public void dispatch(EmailNotification notification) {
        NotificationHandler handler = handlers.get(notification.getType());
        if(handler == null) {
            return;
        }
        handler.handleNotification(notification);
    }
}
