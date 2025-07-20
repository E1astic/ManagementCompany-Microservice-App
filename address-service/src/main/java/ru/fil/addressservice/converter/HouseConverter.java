package ru.fil.addressservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.addressservice.model.dto.house.HouseRegisterRequest;
import ru.fil.addressservice.model.entity.House;
import ru.fil.addressservice.model.entity.Street;

@Component
public class HouseConverter {

    public House mapToHouse(HouseRegisterRequest houseRegisterRequest, Street street) {
        return House.builder()
                .street(street)
                .number(houseRegisterRequest.getNumber())
                .build();
    }
}
