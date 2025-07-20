package ru.fil.addressservice.model.dto.apartment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentApplicationDto {

    private Integer id;

    private String street;

    private String house;

    private String entrance;

    private Integer floor;

    private String apartment;
}
