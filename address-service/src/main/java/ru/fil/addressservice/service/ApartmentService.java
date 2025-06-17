package ru.fil.addressservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.addressservice.converter.ApartmentConverter;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.elasticsearch.repository.AddressElasticRepository;
import ru.fil.addressservice.elasticsearch.service.AddressElasticService;
import ru.fil.addressservice.exception.ApartmentNotFoundException;
import ru.fil.addressservice.exception.HouseNotFoundException;
import ru.fil.addressservice.model.dto.ApartmentDto;
import ru.fil.addressservice.model.dto.ApartmentRegisterRequest;
import ru.fil.addressservice.model.entity.Apartment;
import ru.fil.addressservice.model.entity.House;
import ru.fil.addressservice.repository.ApartmentRepository;
import ru.fil.addressservice.repository.HouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final HouseRepository houseRepository;
    private final ApartmentConverter apartmentConverter;

    private final AddressElasticRepository addressElasticRepository;

    public List<ApartmentDto> getAllApartmentsWithDetails() {
        return apartmentRepository.findAllWithDetails()
                .stream()
                .map(apartmentConverter::mapToApartmentDto)
                .toList();
    }

    @Cacheable("apartments")
    public ApartmentDto getApartmentWithDetailsById(int id) {
        log.info("Request 'getApartmentById' to DB");
        Apartment apartment = apartmentRepository.findByIdWithDetails(id)
                .orElseThrow(ApartmentNotFoundException::new);
        return apartmentConverter.mapToApartmentDto(apartment);
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
