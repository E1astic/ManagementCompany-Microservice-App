package ru.fil.houseservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentSearchDto implements Serializable {

    private Integer apartmentId;

    private Address address;

    @Data
    @AllArgsConstructor
    public static class Address implements Serializable {

        private String street;

        private String house;

        private String apartment;
    }
}
