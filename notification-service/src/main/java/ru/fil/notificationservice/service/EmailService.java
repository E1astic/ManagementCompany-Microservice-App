package ru.fil.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.fil.common.dto.NotificationBody;
import ru.fil.notificationservice.exception.EmailSendingException;
import ru.fil.common.enums.ApplicationStatus;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendStatusEmail(NotificationBody notification) {
        SimpleMailMessage message = createSimpleMailMessage(
                notification.getEmailTo(), notification.getApplicationId(), notification.getApplicationStatus());
        try {
            mailSender.send(message);
            log.info(String.format("Sending email to address %s", notification.getEmailTo()));
        } catch (MailException e) {
            throw new EmailSendingException();
        }
    }

    private SimpleMailMessage createSimpleMailMessage(String emailTo, Integer appId,
                                                      ApplicationStatus applicationStatus) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dinoroma2000@gmail.com");
        message.setTo("darkr2004@mail.ru");
        message.setSubject("Умедомление о смене статуса заявки");
        String status = extractStatusForText(applicationStatus);
        message.setText(String.format("Статус вашей заявки с номером № %d был изменен на '%s'.\n" +
                "Посмотреть все свои заявки можно по ссылке: http://localhost:8091/application/my \nпредварительно " +
                "выполнив вход в свой аккаунт", appId, status));
        return message;
    }

    private String extractStatusForText(ApplicationStatus applicationStatus) {
        return switch (applicationStatus) {
            case ApplicationStatus.UNDER_CONSIDERATION -> "На рассмотрении";
            case ApplicationStatus.IN_PROGRESS -> "В работе";
            case ApplicationStatus.COMPLETED -> "Исполнена";
            case ApplicationStatus.REJECTED -> "Отклонена";
            default -> null;
        };
    }
}
