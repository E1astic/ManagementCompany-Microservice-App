package ru.fil.addressservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.addressservice.model.dto.street.StreetRegisterRequest;
import ru.fil.addressservice.model.entity.Street;

@Component
public class StreetConverter {

    public Street mapToStreet(StreetRegisterRequest streetRegisterDto) {
        return Street.builder()
                .name(streetRegisterDto.getName())
                .build();
    }
}
