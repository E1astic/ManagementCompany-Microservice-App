package ru.fil.houseservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.houseservice.model.dto.StreetDto;
import ru.fil.houseservice.model.entity.Street;

@Component
public class StreetConverter {

    public StreetDto mapToStreetDto(Street street) {
        return new StreetDto(street.getName());
    }
}
