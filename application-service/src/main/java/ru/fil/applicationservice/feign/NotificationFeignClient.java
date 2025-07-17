package ru.fil.applicationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fil.common.enums.ApplicationStatus;

@FeignClient(name = "notification-service")
public interface NotificationFeignClient {

    @GetMapping("/notification/status")
    String sendStatusEmail(@RequestParam("email") String email,
                           @RequestParam("appId") Integer appId,
                           @RequestParam("status") ApplicationStatus status);
}
