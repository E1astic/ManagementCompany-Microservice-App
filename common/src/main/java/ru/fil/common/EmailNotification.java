package ru.fil.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fil.common.enums.NotificationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification {

    protected NotificationType type;

    protected String emailTo;

    protected Integer applicationId;
}
