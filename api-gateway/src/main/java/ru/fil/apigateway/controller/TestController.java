package ru.fil.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/info")
    public String info(Principal principal) {
        if(principal == null) {
            return "Hello, Anonymous!";
        }
        return "Hello, ---" + principal.getName() + "---!";
    }
}
