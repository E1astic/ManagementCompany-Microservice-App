package ru.fil.applicationservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApplicationDto {

    private Integer id;

    private String surname;

    private String name;

    private String patronymic;

    private String phone;

    private String email;

    private Integer apartmentId;
}
