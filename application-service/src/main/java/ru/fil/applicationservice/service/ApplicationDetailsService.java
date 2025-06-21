package ru.fil.applicationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fil.applicationservice.feign.AddressFeignClient;
import ru.fil.applicationservice.feign.AuthFeignClient;
import ru.fil.applicationservice.model.dto.ApartmentApplicationDto;
import ru.fil.applicationservice.model.dto.UserApplicationDto;
import ru.fil.applicationservice.model.entity.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApplicationDetailsService {

    private final AuthFeignClient authFeignClient;
    private final AddressFeignClient addressFeignClient;

    public Map<Integer, UserApplicationDto> getUserDetails(List<Application> applications) {
        List<Integer> userIds = applications
                .stream()
                .map(Application::getAuthorId)
                .distinct()
                .toList();
        List<UserApplicationDto> users = authFeignClient.getUsersByIds(userIds);

        Map<Integer, UserApplicationDto> userDetails = new HashMap<>();
        for(UserApplicationDto user : users) {
            userDetails.put(user.getId(), user);
        }
        return userDetails;
    }

    public Map<Integer, ApartmentApplicationDto> getApartmentDetails(Map<Integer, UserApplicationDto> userDetails) {
        List<Integer> apartmentIds = new ArrayList<>();
        for(UserApplicationDto user : userDetails.values()) {
            apartmentIds.add(user.getApartmentId());
        }
        List<ApartmentApplicationDto> apartments = addressFeignClient.getApartmentsByIds(apartmentIds);

        Map<Integer, ApartmentApplicationDto> apartmentDetails = new HashMap<>();
        for(ApartmentApplicationDto apartment : apartments) {
            apartmentDetails.put(apartment.getId(), apartment);
        }
        return apartmentDetails;
    }
}
