package ru.fil.authservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String surname;

    private String name;

    private String patronymic;

    private LocalDate birthDate;

    private String documentNum;

    private String email;

    private String phone;

    private String password;

    private Integer apartmentId;
}
