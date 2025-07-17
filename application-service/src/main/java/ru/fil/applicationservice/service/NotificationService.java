package ru.fil.applicationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fil.applicationservice.feign.AuthFeignClient;
import ru.fil.applicationservice.kafka.NotificationProducer;
import ru.fil.applicationservice.repository.ApplicationRepository;
import ru.fil.common.dto.NotificationBody;
import ru.fil.common.enums.ApplicationStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ApplicationRepository applicationRepository;
    private final NotificationProducer notificationProducer;
    private final AuthFeignClient authFeignClient;

    public void sendStatusEmail(Integer applicationId, ApplicationStatus applicationStatus) {
        String emailTo = getAuthorEmailFromApplication(applicationId);
        notificationProducer.sendMessage(new NotificationBody(emailTo, applicationId, applicationStatus));
    }

    private String getAuthorEmailFromApplication(int applicationId) {
        int authorId = applicationRepository.findById(applicationId).get().getAuthorId();
        return authFeignClient.getUsersByIds(List.of(authorId)).getFirst().getEmail();
    }
}
