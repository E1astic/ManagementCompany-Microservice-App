package ru.fil.applicationservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.fil.applicationservice.feign.AuthFeignClient;
import ru.fil.applicationservice.feign.NotificationFeignClient;
import ru.fil.applicationservice.model.enums.ApplicationStatus;
import ru.fil.applicationservice.repository.ApplicationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ApplicationRepository applicationRepository;
    private final NotificationFeignClient notificationFeignClient;
    private final AuthFeignClient authFeignClient;

    public void sendStatusEmail(Integer appId, ApplicationStatus applicationStatus) {
        String emailTo = getAuthorEmailFromApplication(appId);
        try {
            notificationFeignClient.sendStatusEmail(emailTo, appId, applicationStatus);
        } catch(FeignException e) {
            if(e.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                log.warn("INTERNAL SERVER ERROR IN NOTIFICATION-SERVICE. Failed to send email-notification.", e);
            }
        }
    }

    private String getAuthorEmailFromApplication(int appId) {
        int authorId = applicationRepository.findById(appId).get().getAuthorId();
        return authFeignClient.getUsersByIds(List.of(authorId)).getFirst().getEmail();
    }
}
