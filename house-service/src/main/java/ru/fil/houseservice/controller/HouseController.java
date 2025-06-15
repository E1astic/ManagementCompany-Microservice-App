package ru.fil.houseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.houseservice.model.dto.ApartmentDto;
import ru.fil.houseservice.model.dto.ApartmentSearchDto;
import ru.fil.houseservice.model.dto.StreetDto;
import ru.fil.houseservice.service.ApartmentService;
import ru.fil.houseservice.service.StreetService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/house")
public class HouseController {

    private final ApartmentService apartmentService;
    private final StreetService streetService;

    @GetMapping("/apartments")
    public ResponseEntity<List<ApartmentDto>> getAllApartments() {
        List<ApartmentDto> apartments = apartmentService.getAllApartmentsWithDetails();
        return ResponseEntity.ok(apartments);
    }

    @GetMapping("/apartments/{id}")
    public ResponseEntity<ApartmentDto> getApartmentById(@PathVariable("id") Integer id) {
        ApartmentDto apartmentDto = apartmentService.getApartmentWithDetailsById(id);
        return ResponseEntity.ok(apartmentDto);
    }

    @GetMapping("/apartments/search")
    public ResponseEntity<List<ApartmentSearchDto>> getAddressesByName(@RequestParam("value") String value) {
        return ResponseEntity.ok(apartmentService.getAllApartmentsWithIdAndDetailsContaining(value));
    }

    @GetMapping("/streets")
    public ResponseEntity<List<StreetDto>> getStreetsByName(@RequestParam("value") String value) {
        return ResponseEntity.ok(streetService.getAllStreetsWithName(value));
    }
}
