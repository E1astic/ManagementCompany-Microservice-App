package ru.fil.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fil.addressservice.model.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
}
