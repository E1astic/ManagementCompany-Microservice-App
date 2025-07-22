package ru.fil.applicationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.fil.applicationservice.feign.AuthFeignClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AuthFeignClient authFeignClient;

    @Cacheable("emails")
    public String getAuthorEmail(Integer authorId) {
        log.info("Getting email of user with ID {} from auth-service before status-change-emailing", authorId);
        return authFeignClient.getUserById(authorId).getEmail();
    }

    @Cacheable("admin-email")
    public String getAdminEmail() {
        log.info("Getting admin's email from auth-service before new-app-emailing");
        return authFeignClient.getAdminEmail();
    }
}
