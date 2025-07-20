package ru.fil.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.addressservice.model.dto.house.HouseRegisterRequest;
import ru.fil.addressservice.model.dto.house.HouseRegisterResponse;
import ru.fil.addressservice.service.HouseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address/houses")
public class HouseController {

    private final HouseService houseService;

    @PostMapping("/add")
    public ResponseEntity<HouseRegisterResponse> saveHouse(
            @RequestBody HouseRegisterRequest houseRegisterRequest) {
        int houseId = houseService.save(houseRegisterRequest);
        return ResponseEntity.ok(new HouseRegisterResponse(houseId));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteHouse(
            @PathVariable("id") Integer id) {
        houseService.deleteById(id);
        return ResponseEntity.ok("Дом был успешно удален");
    }
}
