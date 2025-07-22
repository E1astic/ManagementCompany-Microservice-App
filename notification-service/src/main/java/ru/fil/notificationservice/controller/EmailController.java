package ru.fil.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.common.enums.ApplicationStatus;
import ru.fil.notificationservice.service.EmailService;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/status")
    public String sendStatusEmail(
            @RequestParam("email") String email,
            @RequestParam("appId") Integer appId,
            @RequestParam("status") ApplicationStatus status) {
        //emailService.sendStatusChangeEmail(email, appId, status);
        return "Сообщение успешно отправлено";
    }
}
