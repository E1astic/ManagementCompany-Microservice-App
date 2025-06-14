package ru.fil.houseservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.houseservice.model.dto.ApartmentDto;
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
}
