package ru.fil.applicationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.fil.applicationservice.exception.ApplicationNotFoundException;
import ru.fil.applicationservice.feign.AuthFeignClient;
import ru.fil.applicationservice.kafka.NotificationProducer;
import ru.fil.applicationservice.repository.ApplicationRepository;
import ru.fil.common.EmailNotification;

import ru.fil.common.enums.ApplicationStatus;
import ru.fil.common.enums.NotificationType;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ApplicationRepository applicationRepository;
    private final NotificationProducer notificationProducer;
    private final NotificationFactory notificationFactory;
    private final UserService userService;

    public void sendStatusChangeEmail(Integer applicationId, ApplicationStatus applicationStatus) {
        String emailTo = getAuthorEmailFromApplication(applicationId);
        EmailNotification notification = notificationFactory.createEmailNotification(
                NotificationType.STATUS_CHANGE,
                Map.of("emailTo", emailTo,
                        "applicationId", applicationId,
                        "applicationStatus", applicationStatus));
        notificationProducer.sendMessage(notification);
    }

    public void sendNewApplicationEmail(Integer applicationId) {
        String adminEmail = userService.getAdminEmail();
        EmailNotification notification = notificationFactory.createEmailNotification(
                NotificationType.CREATION,
                Map.of("emailTo", adminEmail,
                        "applicationId", applicationId)
        );
        notificationProducer.sendMessage(notification);
    }

    private String getAuthorEmailFromApplication(int applicationId) {
        Integer authorId = applicationRepository.findById(applicationId).get().getAuthorId();
        System.out.println(authorId);
        if(authorId == null) {
            throw new ApplicationNotFoundException();
        }
        return userService.getAuthorEmail(authorId);
    }
}
