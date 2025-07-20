package ru.fil.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.addressservice.model.dto.street.StreetRegisterRequest;
import ru.fil.addressservice.model.dto.street.StreetRegisterResponse;
import ru.fil.addressservice.service.StreetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address/streets")
public class StreetController {

    private final StreetService streetService;

    @PostMapping("/add")
    public ResponseEntity<StreetRegisterResponse> saveStreet(
            @RequestBody StreetRegisterRequest streetRegisterRequest) {
        int streetId = streetService.save(streetRegisterRequest);
        return ResponseEntity.ok(new StreetRegisterResponse(streetId));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteStreet(
            @PathVariable("id") Integer id) {
        streetService.deleteById(id);
        return ResponseEntity.ok("Улица была успешно удалена");
    }
}
