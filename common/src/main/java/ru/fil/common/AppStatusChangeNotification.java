package ru.fil.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.fil.common.enums.ApplicationStatus;
import ru.fil.common.enums.NotificationType;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppStatusChangeNotification extends EmailNotification {

    private ApplicationStatus applicationStatus;

    public AppStatusChangeNotification(NotificationType notificationType, String emailTo,
                                       Integer applicationId, ApplicationStatus applicationStatus) {
        this.type = notificationType;
        this.emailTo = emailTo;
        this.applicationId = applicationId;
        this.applicationStatus = applicationStatus;
    }
}
