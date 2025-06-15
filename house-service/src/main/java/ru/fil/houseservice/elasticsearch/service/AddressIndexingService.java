package ru.fil.houseservice.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fil.houseservice.converter.ApartmentConverter;
import ru.fil.houseservice.elasticsearch.document.AddressDocument;
import ru.fil.houseservice.elasticsearch.repository.AddressElasticRepository;
import ru.fil.houseservice.repository.ApartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressIndexingService {

    private final ApartmentRepository apartmentRepository;
    private final AddressElasticRepository addressElasticRepository;
    private final ApartmentConverter apartmentConverter;

    public void indexAllAddresses() {
        addressElasticRepository.deleteAll();
        List<AddressDocument> addresses = apartmentRepository.findAllWithDetails()
                .stream()
                .map(apartmentConverter::mapToAddressDocument)
                .toList();
        addressElasticRepository.saveAll(addresses);
    }
}
