package ru.fil.houseservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.houseservice.model.dto.ApartmentDto;
import ru.fil.houseservice.model.dto.ApartmentSearchDto;
import ru.fil.houseservice.model.entity.Apartment;

@Component
public class ApartmentConverter {

    public ApartmentDto mapToApartmentDto(Apartment apartment) {
        return ApartmentDto.builder()
                .house(apartment.getHouse().getNumber())
                .street(apartment.getHouse().getStreet().getName())
                .apartment(apartment.getApartmentNum())
                .build();
    }

    public ApartmentSearchDto mapToApartmentSearchDto(Apartment apartment) {
        return ApartmentSearchDto.builder()
                .apartmentId(apartment.getId())
                .address(new ApartmentSearchDto.Address(
                        apartment.getHouse().getStreet().getName(),
                        apartment.getHouse().getNumber(),
                        apartment.getApartmentNum()))
                .build();
    }
}
