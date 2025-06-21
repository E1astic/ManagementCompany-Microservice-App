package ru.fil.applicationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fil.applicationservice.model.dto.ApartmentApplicationDto;

import java.util.List;

@FeignClient(name = "address-service")
public interface AddressFeignClient {

    @GetMapping("address/apartments/forApp")
    List<ApartmentApplicationDto> getApartmentsByIds(@RequestParam List<Integer> ids);
}
