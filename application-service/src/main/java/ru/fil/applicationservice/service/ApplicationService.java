package ru.fil.applicationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.applicationservice.converter.ApplicationConverter;
import ru.fil.applicationservice.exception.ApplicationNotFoundException;
import ru.fil.applicationservice.exception.IncorrectStatusException;
import ru.fil.applicationservice.model.dto.ApartmentApplicationDto;
import ru.fil.applicationservice.model.dto.ApplicationCreationRequest;
import ru.fil.applicationservice.model.dto.ApplicationFullDto;
import ru.fil.applicationservice.model.dto.ApplicationPersonalDto;
import ru.fil.applicationservice.model.dto.UserApplicationDto;
import ru.fil.applicationservice.model.entity.Application;
import ru.fil.applicationservice.model.enums.ApplicationStatus;
import ru.fil.applicationservice.repository.ApplicationRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationConverter applicationConverter;
    private final JwtService jwtService;
    private final ApplicationDetailsService applicationDetailsService;
    private final NotificationService notificationService;

    @Transactional
    public int createApplication(String authHeader, ApplicationCreationRequest applicationCreationDto) {
        int userId = jwtService.getUserIdFromHeader(authHeader);
        Application application = applicationRepository.save(applicationConverter
                .mapToApplication(applicationCreationDto, userId));

        // отправить уведомление админам а создании новой заявки

        return application.getId();
    }

    public List<ApplicationPersonalDto> getPersonalApplications(String authHeader) {
        int userId = jwtService.getUserIdFromHeader(authHeader);
        return applicationRepository.findByAuthorId(userId)
                .stream()
                .map(applicationConverter::mapToApplicationPersonalDto)
                .toList();
    }

    @Transactional
    public void updateApplicationStatus(int id, String status) {
        ApplicationStatus applicationStatus;
        try {
            applicationStatus = ApplicationStatus.valueOf(status.toUpperCase());
        } catch(IllegalArgumentException e) {
            throw new IncorrectStatusException();
        }
        int rowsUpdated = applicationRepository.updateStatusById(id, applicationStatus);
        if(rowsUpdated == 0) {
            throw new ApplicationNotFoundException();
        } else {
            notificationService.sendStatusEmail(id, applicationStatus);
        }
    }

    @Transactional(readOnly = true)
    public List<ApplicationFullDto> getAllApplicationsWithDetails() {
        List<Application> applications = applicationRepository.findAll();
        Map<Integer, UserApplicationDto> userDetails = applicationDetailsService.getUserDetails(applications);
        Map<Integer, ApartmentApplicationDto> apartmentDetails = applicationDetailsService.getApartmentDetails(userDetails);

        return applications
                .stream()
                .map(app -> applicationConverter.mapToApplicationFullDto(app, userDetails, apartmentDetails))
                .toList();
    }
 }
