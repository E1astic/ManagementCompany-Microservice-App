package ru.fil.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service")
public interface AddressFeignClient {

    @GetMapping("/address/apartments/{id}")
    ResponseEntity<?> getApartmentById(@PathVariable("id") Integer id);
}
