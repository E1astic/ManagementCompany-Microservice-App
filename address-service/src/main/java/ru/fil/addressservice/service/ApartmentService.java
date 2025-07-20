package ru.fil.addressservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.addressservice.converter.ApartmentConverter;
import ru.fil.addressservice.elasticsearch.repository.AddressElasticRepository;
import ru.fil.addressservice.exception.ApartmentNotFoundException;
import ru.fil.addressservice.exception.HouseNotFoundException;
import ru.fil.addressservice.model.dto.apartment.ApartmentApplicationDto;
import ru.fil.addressservice.model.dto.apartment.ApartmentSimpleDto;
import ru.fil.addressservice.model.dto.apartment.ApartmentRegisterRequest;
import ru.fil.addressservice.model.entity.Apartment;
import ru.fil.addressservice.model.entity.House;
import ru.fil.addressservice.repository.ApartmentRepository;
import ru.fil.addressservice.repository.HouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final HouseRepository houseRepository;
    private final ApartmentConverter apartmentConverter;

    private final AddressElasticRepository addressElasticRepository;

    public List<ApartmentSimpleDto> getAllApartments() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentConverter::mapToApartmentSimpleDto)
                .toList();
    }

    public List<ApartmentApplicationDto> getAllApartmentsWithDetails() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentConverter::mapToApartmentApplicationDto)
                .toList();
    }

    public List<ApartmentApplicationDto> getAllApartmentsWithDetailsByIdIn(List<Integer> ids) {
        if(ids == null || ids.isEmpty()) {
            return getAllApartmentsWithDetails();
        }
        return apartmentRepository.findAllByIdIn(ids)
                .stream()
                .map(apartmentConverter::mapToApartmentApplicationDto)
                .toList();
    }

    @Cacheable("apartments")
    public ApartmentSimpleDto getApartmentById(int id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(ApartmentNotFoundException::new);
        return apartmentConverter.mapToApartmentSimpleDto(apartment);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "apartments", key = "#result"),
            @CacheEvict(cacheNames = "addresses", allEntries = true)
    })
    public Integer save(ApartmentRegisterRequest apartmentRegisterRequest) {
        House house = houseRepository.findById(apartmentRegisterRequest.getHouseId())
                .orElseThrow(HouseNotFoundException::new);
        Apartment apartment = apartmentRepository.save(apartmentConverter
                .mapToApartment(apartmentRegisterRequest, house));
        house.addApartment(apartment);

        addressElasticRepository.save(apartmentConverter.mapToAddressDocument(apartment));
        return apartment.getId();
    }
}
