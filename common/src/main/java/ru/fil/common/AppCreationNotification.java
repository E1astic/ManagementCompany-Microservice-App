package ru.fil.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.fil.common.enums.NotificationType;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppCreationNotification extends EmailNotification {

    public AppCreationNotification(NotificationType notificationType, String emailTo, Integer applicationId) {
        super(notificationType, emailTo, applicationId);
    }
}
