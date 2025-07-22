package ru.fil.notificationservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import ru.fil.common.enums.ApplicationStatus;

@Component
public class EmailServiceHelper {

    public SimpleMailMessage createStatusChangeMailMessage(String emailTo, Integer applicationId,
                                                            ApplicationStatus applicationStatus) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dinoroma2000@gmail.com");
        message.setTo("darkr2004@mail.ru");  // emailTo
        message.setSubject("Умедомление о смене статуса заявки");
        String status = extractStatusForText(applicationStatus);
        message.setText(String.format("Статус вашей заявки с номером № %d был изменен на '%s'.\n" +
                "Посмотреть все свои заявки можно по ссылке: http://localhost:8091/application/my \nпредварительно " +
                "выполнив вход в свой аккаунт", applicationId, status));
        return message;
    }

    public SimpleMailMessage createNewAppMailMessage(String adminEmail, Integer applicationId){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dinoroma2000@gmail.com");
        message.setTo("darkr2004@mail.ru");  // adminEmail
        message.setSubject("Уведомление о создании новой заявки");
        message.setText(String.format("Была создана новая заявка с номером № %d.\n" +
                "Посмотреть все заявки администратору можно по ссылке: http://localhost:8091/application/all", applicationId));
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
