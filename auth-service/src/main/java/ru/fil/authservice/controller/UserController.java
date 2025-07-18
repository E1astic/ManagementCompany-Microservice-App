package ru.fil.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.authservice.model.dto.UserApplicationDto;
import ru.fil.authservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/forApp")
    public List<UserApplicationDto> getUsersByIds(@RequestParam("ids") List<Integer> userIds) {
        return userService.getAllUserApplicationDto(userIds);
    }
}
