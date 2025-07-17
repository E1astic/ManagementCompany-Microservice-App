package ru.fil.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.elasticsearch.service.AddressElasticService;
import ru.fil.addressservice.model.dto.ApartmentApplicationDto;
import ru.fil.addressservice.model.dto.ApartmentSimpleDto;
import ru.fil.addressservice.model.dto.ApartmentRegisterRequest;
import ru.fil.addressservice.model.dto.ApartmentRegisterResponse;
import ru.fil.addressservice.model.dto.HouseRegisterRequest;
import ru.fil.addressservice.model.dto.HouseRegisterResponse;
import ru.fil.addressservice.model.dto.StreetRegisterRequest;
import ru.fil.addressservice.model.dto.StreetRegisterResponse;
import ru.fil.addressservice.service.ApartmentService;
import ru.fil.addressservice.service.HouseService;
import ru.fil.addressservice.service.StreetService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final ApartmentService apartmentService;
    private final StreetService streetService;
    private final HouseService houseService;
    private final AddressElasticService addressElasticService;

    @GetMapping("/apartments")
    public ResponseEntity<List<ApartmentSimpleDto>> getAllApartments() {
        List<ApartmentSimpleDto> apartments = apartmentService.getAllApartments();
        return ResponseEntity.ok(apartments);
    }

    @GetMapping("/apartments/{id}")
    public ResponseEntity<ApartmentSimpleDto> getApartmentById(@PathVariable("id") Integer id) {
        ApartmentSimpleDto apartmentDto = apartmentService.getApartmentById(id);
        return ResponseEntity.ok(apartmentDto);
    }

    @GetMapping("/apartments/forApp")
    public List<ApartmentApplicationDto> getApartmentsByIds(@RequestParam List<Integer> ids) {
        return apartmentService.getAllApartmentsWithDetailsByIdIn(ids);
    }

    @GetMapping("/apartments/search")
    public ResponseEntity<List<AddressDocument>> findAddressesWithFuzzy(@RequestParam("value") String value) {
        return ResponseEntity.ok(addressElasticService.findAddressesWithFuzzy(value));
    }

    @PostMapping("/apartments/add")
    public ResponseEntity<ApartmentRegisterResponse> saveApartment(
            @RequestBody ApartmentRegisterRequest apartmentRegisterRequest) {
        int apartmentId = apartmentService.save(apartmentRegisterRequest);
        return ResponseEntity.ok(new ApartmentRegisterResponse(apartmentId));
    }

    @PostMapping("/streets/add")
    public ResponseEntity<StreetRegisterResponse> saveStreet(
            @RequestBody StreetRegisterRequest streetRegisterRequest) {
        int streetId = streetService.save(streetRegisterRequest);
        return ResponseEntity.ok(new StreetRegisterResponse(streetId));
    }

    @DeleteMapping("/streets/del/{id}")
    public ResponseEntity<?> deleteStreet(
            @PathVariable("id") Integer id) {
        streetService.deleteById(id);
        return ResponseEntity.ok("Улица была успешно удалена");
    }

    @PostMapping("/houses/add")
    public ResponseEntity<HouseRegisterResponse> saveHouse(
            @RequestBody HouseRegisterRequest houseRegisterRequest) {
        int houseId = houseService.save(houseRegisterRequest);
        return ResponseEntity.ok(new HouseRegisterResponse(houseId));
    }

    @DeleteMapping("/houses/del/{id}")
    public ResponseEntity<?> deleteHouse(
            @PathVariable("id") Integer id) {
        houseService.deleteById(id);
        return ResponseEntity.ok("Дом был успешно удален");
    }
}
