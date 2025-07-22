package ru.fil.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.fil.notificationservice.exception.EmailSendingException;
import ru.fil.common.enums.ApplicationStatus;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailServiceHelper emailServiceHelper;

    public void sendStatusChangeEmail(String emailTo, Integer applicationId, ApplicationStatus applicationStatus) {
        SimpleMailMessage message = emailServiceHelper.createStatusChangeMailMessage(
                emailTo, applicationId, applicationStatus);
        try {
            mailSender.send(message);
            log.info("Sending status-change-email to address {}", emailTo);
        } catch (MailException e) {
            throw new EmailSendingException();
        }
    }

    public void sendNewAppEmailToAdmin(String adminEmail, Integer applicationId) {
        SimpleMailMessage message = emailServiceHelper.createNewAppMailMessage(adminEmail, applicationId);
        try {
            mailSender.send(message);
            log.info("Sending new-app-email to admin address {}", adminEmail);
        } catch (MailException e) {
            throw new EmailSendingException();
        }
    }
}
