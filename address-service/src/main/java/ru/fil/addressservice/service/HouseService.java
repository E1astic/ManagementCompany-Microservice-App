package ru.fil.addressservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.addressservice.converter.HouseConverter;
import ru.fil.addressservice.elasticsearch.repository.AddressElasticRepository;
import ru.fil.addressservice.exception.HouseNotFoundException;
import ru.fil.addressservice.exception.StreetNotFoundException;
import ru.fil.addressservice.model.dto.HouseRegisterRequest;
import ru.fil.addressservice.model.entity.Apartment;
import ru.fil.addressservice.model.entity.House;
import ru.fil.addressservice.model.entity.Street;
import ru.fil.addressservice.repository.ApartmentRepository;
import ru.fil.addressservice.repository.HouseRepository;
import ru.fil.addressservice.repository.StreetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final StreetRepository streetRepository;
    private final AddressElasticRepository addressElasticRepository;
    private final HouseConverter houseConverter;

    @Transactional
    public Integer save(HouseRegisterRequest houseRegisterRequest) {
        Street street = streetRepository.findById(houseRegisterRequest.getStreetId())
                .orElseThrow(StreetNotFoundException::new);
        House house = houseRepository.save(houseConverter.mapToHouse(houseRegisterRequest, street));
        street.addHouse(house);
        return house.getId();
    }

    @Transactional
    public void deleteById(int id) {
        House house = houseRepository.findById(id).orElseThrow(HouseNotFoundException::new);
        List<Integer> apartmentIds = house.getApartments()
                .stream()
                .map(Apartment::getId)
                .toList();
        addressElasticRepository.deleteByApartmentIdIn(apartmentIds);
        houseRepository.delete(house);
    }
}
