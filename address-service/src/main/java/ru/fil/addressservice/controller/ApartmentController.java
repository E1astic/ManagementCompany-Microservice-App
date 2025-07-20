package ru.fil.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.addressservice.elasticsearch.document.AddressDocument;
import ru.fil.addressservice.elasticsearch.service.AddressElasticService;
import ru.fil.addressservice.model.dto.apartment.ApartmentApplicationDto;
import ru.fil.addressservice.model.dto.apartment.ApartmentRegisterRequest;
import ru.fil.addressservice.model.dto.apartment.ApartmentRegisterResponse;
import ru.fil.addressservice.model.dto.apartment.ApartmentSimpleDto;
import ru.fil.addressservice.service.ApartmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final AddressElasticService addressElasticService;

    @GetMapping
    public ResponseEntity<List<ApartmentSimpleDto>> getAllApartments() {
        List<ApartmentSimpleDto> apartments = apartmentService.getAllApartments();
        return ResponseEntity.ok(apartments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentSimpleDto> getApartmentById(@PathVariable("id") Integer id) {
        ApartmentSimpleDto apartmentDto = apartmentService.getApartmentById(id);
        return ResponseEntity.ok(apartmentDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AddressDocument>> findAddressesWithFuzzy(@RequestParam("value") String value) {
        return ResponseEntity.ok(addressElasticService.findAddressesWithFuzzy(value));
    }

    @PostMapping("/add")
    public ResponseEntity<ApartmentRegisterResponse> saveApartment(
            @RequestBody ApartmentRegisterRequest apartmentRegisterRequest) {
        int apartmentId = apartmentService.save(apartmentRegisterRequest);
        return ResponseEntity.ok(new ApartmentRegisterResponse(apartmentId));
    }

    @GetMapping("/forApp")
    public List<ApartmentApplicationDto> getApartmentsByIds(@RequestParam List<Integer> ids) {
        return apartmentService.getAllApartmentsWithDetailsByIdIn(ids);
    }
}
