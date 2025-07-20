package ru.fil.addressservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "auth-service")
public interface AuthFeignClient {

    @DeleteMapping("/user/del/apart")
    int deleteUsersByApartmentId(@RequestParam("apartmentIds") List<Integer> apartmentIds);
}
