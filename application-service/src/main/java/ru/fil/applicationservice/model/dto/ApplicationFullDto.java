package ru.fil.applicationservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fil.common.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationFullDto {

    private Integer number;

    private LocalDateTime createdAt;

    private ApplicationStatus status;

    private String authorSurname;

    private String authorName;

    private String authorPatronymic;

    private String authorEmail;

    private String authorPhone;

    private Integer authorApartmentId;

    private String authorStreet;

    private String authorHouse;

    private String authorEntrance;

    private Integer authorFloor;

    private String authorApartment;

    private String topic;

    private String description;

    private String requirement;
}
