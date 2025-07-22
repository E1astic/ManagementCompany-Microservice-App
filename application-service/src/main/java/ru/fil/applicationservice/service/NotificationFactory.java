package ru.fil.applicationservice.service;

import org.springframework.stereotype.Component;
import ru.fil.common.AppCreationNotification;
import ru.fil.common.AppStatusChangeNotification;
import ru.fil.common.EmailNotification;
import ru.fil.common.enums.ApplicationStatus;
import ru.fil.common.enums.NotificationType;

import java.util.Map;

@Component
public class NotificationFactory {

    public EmailNotification createEmailNotification(
            NotificationType type, Map<String, Object> fields) {
        String emailTo;
        Integer applicationId;
        switch (type) {
            case NotificationType.STATUS_CHANGE:
                emailTo = (String) fields.get("emailTo");
                applicationId = (Integer) fields.get("applicationId");
                ApplicationStatus applicationStatus = (ApplicationStatus) fields.get("applicationStatus");
                return new AppStatusChangeNotification(
                        NotificationType.STATUS_CHANGE, emailTo, applicationId, applicationStatus);
            case NotificationType.CREATION:
                emailTo = (String) fields.get("emailTo");
                applicationId = (Integer) fields.get("applicationId");
                return new AppCreationNotification(NotificationType.CREATION, emailTo, applicationId);
            default:
                throw new IllegalArgumentException("Invalid notification type");
        }
    }
}
