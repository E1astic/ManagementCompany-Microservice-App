package ru.fil.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fil.common.enums.ApplicationStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationBody {

    private String emailTo;

    private Integer applicationId;

    private ApplicationStatus applicationStatus;
}
