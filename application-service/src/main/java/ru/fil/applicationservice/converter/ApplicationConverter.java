package ru.fil.applicationservice.converter;

import org.springframework.stereotype.Component;
import ru.fil.applicationservice.model.dto.ApartmentApplicationDto;
import ru.fil.applicationservice.model.dto.ApplicationCreationRequest;
import ru.fil.applicationservice.model.dto.ApplicationFullDto;
import ru.fil.applicationservice.model.dto.ApplicationPersonalDto;
import ru.fil.applicationservice.model.dto.UserApplicationDto;
import ru.fil.applicationservice.model.entity.Application;
import ru.fil.applicationservice.model.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class ApplicationConverter {

    public Application mapToApplication(ApplicationCreationRequest applicationCreationDto, Integer authorId) {
        return Application.builder()
                .createdAt(LocalDateTime.now())
                .authorId(authorId)
                .topic(applicationCreationDto.getTopic())
                .description(applicationCreationDto.getDescription())
                .requirement(applicationCreationDto.getRequirement())
                .status(ApplicationStatus.CREATED)
                .build();
    }

    public ApplicationPersonalDto mapToApplicationPersonalDto(Application application) {
        return ApplicationPersonalDto.builder()
                .number(application.getId())
                .createdAt(application.getCreatedAt())
                .status(application.getStatus())
                .topic(application.getTopic())
                .description(application.getDescription())
                .requirement(application.getRequirement())
                .build();
    }

    public ApplicationFullDto mapToApplicationFullDto(Application application,
                                                      Map<Integer, UserApplicationDto> users,
                                                      Map<Integer, ApartmentApplicationDto> apartments) {
        UserApplicationDto currUser = users.get(application.getAuthorId());
        ApartmentApplicationDto currApartment = apartments.get(currUser.getApartmentId());
        return ApplicationFullDto.builder()
                .number(application.getId())
                .createdAt(application.getCreatedAt())
                .status(application.getStatus())
                .authorSurname(currUser.getSurname())
                .authorName(currUser.getName())
                .authorPatronymic(currUser.getPatronymic())
                .authorEmail(currUser.getEmail())
                .authorPhone(currUser.getPhone())
                .authorApartmentId(currUser.getApartmentId())
                .authorStreet(currApartment.getStreet())
                .authorHouse(currApartment.getHouse())
                .authorEntrance(currApartment.getEntrance())
                .authorFloor(currApartment.getFloor())
                .authorApartment(currApartment.getApartment())
                .topic(application.getTopic())
                .description(application.getDescription())
                .requirement(application.getRequirement())
                .build();
    }
}
