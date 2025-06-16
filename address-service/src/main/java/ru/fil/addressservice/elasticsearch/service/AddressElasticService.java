package ru.fil.addressservice.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.elasticsearch.repository.AddressElasticRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressElasticService {

    private final AddressElasticRepository addressElasticRepository;

    @Cacheable("addresses")
    public List<AddressDocument> findAddressesWithFuzzy(String value) {
        log.info("Request 'findAddressWithFuzzy' to DB (elasticsearch)");
        return addressElasticRepository.searchByFullAddressWithFuzzy(value);
    }
}
