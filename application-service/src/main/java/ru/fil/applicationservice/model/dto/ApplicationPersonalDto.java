package ru.fil.applicationservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fil.applicationservice.model.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationPersonalDto {

    private Integer number;

    private LocalDateTime createdAt;

    private ApplicationStatus status;

    private String topic;

    private String description;

    private String requirement;
}
