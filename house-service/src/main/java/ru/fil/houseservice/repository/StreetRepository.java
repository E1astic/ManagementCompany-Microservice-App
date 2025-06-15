package ru.fil.houseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fil.houseservice.model.entity.Street;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {

    List<Street> findByNameContaining(String name);
}
