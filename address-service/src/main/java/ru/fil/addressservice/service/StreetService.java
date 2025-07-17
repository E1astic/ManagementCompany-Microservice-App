package ru.fil.addressservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.addressservice.converter.StreetConverter;
import ru.fil.addressservice.elasticsearch.repository.AddressElasticRepository;
import ru.fil.addressservice.exception.StreetNotFoundException;
import ru.fil.addressservice.model.dto.StreetRegisterRequest;
import ru.fil.addressservice.model.entity.Apartment;
import ru.fil.addressservice.model.entity.House;
import ru.fil.addressservice.model.entity.Street;
import ru.fil.addressservice.repository.ApartmentRepository;
import ru.fil.addressservice.repository.StreetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StreetService {

    private final StreetRepository streetRepository;
    private final ApartmentRepository apartmentRepository;
    private final AddressElasticRepository addressElasticRepository;
    private final StreetConverter streetConverter;
    private final RedisCacheService redisCacheService;

    @Transactional
    public Integer save(StreetRegisterRequest streetRegisterDto) {
        Street street = streetRepository.save(streetConverter.mapToStreet(streetRegisterDto));
        return street.getId();
    }

    @Transactional
    @CacheEvict(cacheNames = "addresses", allEntries = true)
    public void deleteById(int id) {
        Street street = streetRepository.findByIdWithHouses(id).orElseThrow(StreetNotFoundException::new);
        List<Integer> houseIds = street.getHouses()
                .stream()
                .map(House::getId)
                .toList();
        List<Integer> apartmentIds = apartmentRepository.findByHouseIdIn(houseIds)
                .stream()
                .map(Apartment::getId)
                .toList();
        addressElasticRepository.deleteByApartmentIdIn(apartmentIds);
        streetRepository.delete(street);
        redisCacheService.evictApartmentsCache(apartmentIds);
    }
}
