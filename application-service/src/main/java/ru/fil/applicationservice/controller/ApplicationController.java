package ru.fil.applicationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.applicationservice.model.dto.ApplicationCreationRequest;
import ru.fil.applicationservice.model.dto.ApplicationCreationResponse;
import ru.fil.applicationservice.model.dto.ApplicationFullDto;
import ru.fil.applicationservice.model.dto.ApplicationPersonalDto;
import ru.fil.applicationservice.model.dto.SimpleMessageResponse;
import ru.fil.applicationservice.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationFullDto>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplicationsWithDetails());
    }

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationPersonalDto>> getMyApplications(
            @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(applicationService.getPersonalApplications(authHeader));
    }

    @PostMapping("/new")
    public ResponseEntity<ApplicationCreationResponse> createApplication(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ApplicationCreationRequest applicationCreationDto) {
        int applicationId = applicationService.createApplication(authHeader, applicationCreationDto);
        return ResponseEntity.ok(new ApplicationCreationResponse(applicationId));
    }

    @PatchMapping("/update/{id}/{status}")
    public ResponseEntity<SimpleMessageResponse> updateApplicationStatus(
            @PathVariable("id") Integer id, @PathVariable("status") String status) {
        applicationService.updateApplicationStatus(id, status);
        return ResponseEntity.ok(new SimpleMessageResponse("Статус заявки успешно обновлен"));
    }
}
