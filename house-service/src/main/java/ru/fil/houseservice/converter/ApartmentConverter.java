package ru.fil.houseservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.houseservice.elasticsearch.document.AddressDocument;
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

    public AddressDocument mapToAddressDocument(Apartment apartment) {
        AddressDocument addressDocument = AddressDocument.builder()
                .street(apartment.getHouse().getStreet().getName())
                .house(apartment.getHouse().getNumber())
                .entrance(apartment.getEntranceNum())
                .apartment(apartment.getApartmentNum())
                .apartmentId(apartment.getId())
                .build();
        addressDocument.updateFullAddress();
        return addressDocument;
    }
}
