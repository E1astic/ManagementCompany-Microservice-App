package ru.fil.addressservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentRegisterRequest {

    private Integer houseId;

    private String entranceNum;

    private Integer floorNum;

    private String apartmentNum;

    private BigDecimal area;
}
