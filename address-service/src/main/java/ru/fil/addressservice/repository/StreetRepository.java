package ru.fil.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fil.addressservice.model.entity.Street;

import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {

    @Query("SELECT s FROM Street s JOIN FETCH s.houses WHERE s.id = :id")
    Optional<Street> findByIdWithHouses(@Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM Streets s WHERE s.id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") Integer id);
}
