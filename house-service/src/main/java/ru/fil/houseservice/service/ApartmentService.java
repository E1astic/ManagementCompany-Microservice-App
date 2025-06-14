package ru.fil.houseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fil.houseservice.converter.ApartmentConverter;
import ru.fil.houseservice.exception.ApartmentNotFoundException;
import ru.fil.houseservice.model.dto.ApartmentDto;
import ru.fil.houseservice.model.entity.Apartment;
import ru.fil.houseservice.repository.ApartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    private final ApartmentConverter apartmentConverter;

    public List<ApartmentDto> getAllApartmentsWithDetails() {
        return apartmentRepository.findAllWithDetails()
                .stream()
                .map(apartmentConverter::mapToApartmentDto)
                .toList();
    }

    public ApartmentDto getApartmentWithDetailsById(int id) {
        Apartment apartment = apartmentRepository.findByIdWithDetails(id).orElseThrow(ApartmentNotFoundException::new);
        return apartmentConverter.mapToApartmentDto(apartment);
    }
}
