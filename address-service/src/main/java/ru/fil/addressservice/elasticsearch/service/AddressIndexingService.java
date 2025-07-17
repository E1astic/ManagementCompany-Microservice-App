package ru.fil.addressservice.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fil.addressservice.converter.ApartmentConverter;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.elasticsearch.repository.AddressElasticRepository;
import ru.fil.addressservice.repository.ApartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressIndexingService {

    private final ApartmentRepository apartmentRepository;
    private final AddressElasticRepository addressElasticRepository;
    private final ApartmentConverter apartmentConverter;

    public void indexAllAddresses() {
        addressElasticRepository.deleteAll();
        List<AddressDocument> addresses = apartmentRepository.findAll()
                .stream()
                .map(apartmentConverter::mapToAddressDocument)
                .toList();
        addressElasticRepository.saveAll(addresses);
    }
}
