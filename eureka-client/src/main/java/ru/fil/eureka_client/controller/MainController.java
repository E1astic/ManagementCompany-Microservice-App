package ru.fil.eureka_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/shared")
    public String shared() {
        return "Welcome to SHARED PAGE";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Welcome to SECURED page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "ADMIN";
    }

    @GetMapping("/info")
    public String info(Principal principal) {
        return "Hello, ---" + principal.getName() + "---!";
    }
}
