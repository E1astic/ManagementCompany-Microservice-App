package ru.fil.addressservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.model.dto.ApartmentDto;
import ru.fil.addressservice.model.dto.ApartmentRegisterRequest;
import ru.fil.addressservice.model.entity.Apartment;
import ru.fil.addressservice.model.entity.House;

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
                .apartmentId(apartment.getId())
                .street(apartment.getHouse().getStreet().getName())
                .house(apartment.getHouse().getNumber())
                .entrance(apartment.getEntranceNum())
                .apartment(apartment.getApartmentNum())
                .build();
        addressDocument.updateFullAddress();
        return addressDocument;
    }

    public Apartment mapToApartment(ApartmentRegisterRequest apartmentRegisterRequest, House house) {
        return Apartment.builder()
                .house(house)
                .entranceNum(apartmentRegisterRequest.getEntranceNum())
                .floorNum(apartmentRegisterRequest.getFloorNum())
                .apartmentNum(apartmentRegisterRequest.getApartmentNum())
                .area(apartmentRegisterRequest.getArea())
                .build();
    }
}
