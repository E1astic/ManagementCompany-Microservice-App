package ru.fil.houseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fil.houseservice.model.entity.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
}
