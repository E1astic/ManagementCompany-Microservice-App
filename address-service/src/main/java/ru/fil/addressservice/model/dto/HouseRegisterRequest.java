package ru.fil.addressservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseRegisterRequest {

    private Integer streetId;

    private String number;
}
