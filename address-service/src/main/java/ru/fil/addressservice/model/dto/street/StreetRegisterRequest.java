package ru.fil.addressservice.model.dto.street;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreetRegisterRequest {

    private String name;
}
