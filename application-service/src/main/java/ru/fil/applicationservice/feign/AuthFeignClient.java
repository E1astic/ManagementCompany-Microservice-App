package ru.fil.applicationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fil.applicationservice.model.dto.UserApplicationDto;

import java.util.List;

@FeignClient(name = "auth-service")
public interface AuthFeignClient {

    @GetMapping("/user/forApp")
    UserApplicationDto getUserById(@RequestParam("id") Integer id);

    @GetMapping("/user/all/forApp")
    List<UserApplicationDto> getUsersByIds(@RequestParam("ids") List<Integer> userIds);

    @GetMapping("/user/admin/forApp")
    String getAdminEmail();
}
