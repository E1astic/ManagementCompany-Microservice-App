package ru.fil.addressservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.model.dto.apartment.ApartmentApplicationDto;
import ru.fil.addressservice.model.dto.apartment.ApartmentSimpleDto;
import ru.fil.addressservice.model.dto.apartment.ApartmentRegisterRequest;
import ru.fil.addressservice.model.entity.Apartment;
import ru.fil.addressservice.model.entity.House;

@Component
public class ApartmentConverter {

    public ApartmentSimpleDto mapToApartmentSimpleDto(Apartment apartment) {
        return ApartmentSimpleDto.builder()
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

    public ApartmentApplicationDto mapToApartmentApplicationDto(Apartment apartment) {
        return ApartmentApplicationDto.builder()
                .id(apartment.getId())
                .street(apartment.getHouse().getStreet().getName())
                .house(apartment.getHouse().getNumber())
                .entrance(apartment.getEntranceNum())
                .floor(apartment.getFloorNum())
                .apartment(apartment.getApartmentNum())
                .build();
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
