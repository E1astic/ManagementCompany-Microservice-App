package ru.fil.houseservice.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.fil.houseservice.elasticsearch.document.AddressDocument;
import ru.fil.houseservice.elasticsearch.repository.AddressElasticRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressElasticService {

    private final AddressElasticRepository addressElasticRepository;

    @Cacheable("addresses")
    public List<AddressDocument> findAddressesWithFuzzy(String value) {
        return addressElasticRepository.searchByFullAddressWithFuzzy(value);
    }
}
