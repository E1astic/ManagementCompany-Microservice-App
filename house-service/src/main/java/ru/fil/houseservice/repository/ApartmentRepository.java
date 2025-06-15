package ru.fil.houseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fil.houseservice.model.entity.Apartment;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    @Query("SELECT a FROM Apartment a JOIN FETCH a.house h JOIN FETCH h.street")
    List<Apartment> findAllWithDetails();

    @Query("SELECT a FROM Apartment a JOIN FETCH a.house h JOIN FETCH h.street s WHERE " +
            "CONCAT(s.name,' ', h.number, ' ', a.entranceNum, ' ', a.apartmentNum) LIKE CONCAT('%', :value, '%') OR " +
            "a.entranceNum LIKE CONCAT('%', :value, '%') OR " +
            "a.apartmentNum LIKE CONCAT('%', :value, '%') OR " +
            "h.number LIKE CONCAT('%', :value, '%') OR " +
            "s.name LIKE CONCAT('%', :value, '%')")
    List<Apartment> findAllWithDetailsContaining(@Param("value") String value);

    @Query("SELECT a FROM Apartment a JOIN FETCH a.house h JOIN FETCH h.street WHERE a.id = :id")
    Optional<Apartment> findByIdWithDetails(@Param("id") int id);
}
