package ru.fil.houseservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.fil.houseservice.converter.ApartmentConverter;
import ru.fil.houseservice.converter.StreetConverter;
import ru.fil.houseservice.model.dto.ApartmentSearchDto;
import ru.fil.houseservice.model.dto.StreetDto;
import ru.fil.houseservice.repository.ApartmentRepository;
import ru.fil.houseservice.repository.StreetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StreetService {

    private final StreetRepository streetRepository;
    private final StreetConverter streetConverter;
    private final ApartmentRepository apartmentRepository;
    private final ApartmentConverter apartmentConverter;

    @Cacheable("streets")
    public List<StreetDto> getAllStreetsWithName(String name) {
        log.info(String.format("Request with {%s} processing by DATABASE", name));
        return streetRepository.findByNameContaining(name)
                .stream()
                .map(streetConverter::mapToStreetDto)
                .toList();
    }
}
