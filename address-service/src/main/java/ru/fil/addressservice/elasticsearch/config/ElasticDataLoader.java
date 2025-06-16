package ru.fil.addressservice.elasticsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.fil.addressservice.elasticsearch.service.AddressIndexingService;

@Component
@RequiredArgsConstructor
public class ElasticDataLoader implements ApplicationRunner {

    private final AddressIndexingService addressIndexingService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addressIndexingService.indexAllAddresses();
    }
}
