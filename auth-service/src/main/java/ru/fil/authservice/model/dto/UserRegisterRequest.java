package ru.fil.authservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String email;

    private String password;

    private String name;

    private String surname;

    private Integer birthYear;
}
