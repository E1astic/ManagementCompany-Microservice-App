package ru.fil.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fil.addressservice.model.entity.Apartment;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    @Query("SELECT a FROM Apartment a JOIN FETCH a.house h JOIN FETCH h.street")
    List<Apartment> findAll();

    @Query("SELECT a FROM Apartment a JOIN FETCH a.house h JOIN FETCH h.street WHERE a.id IN :ids")
    List<Apartment> findAllByIdIn(@Param("ids") List<Integer> ids);

    @Query("SELECT a FROM Apartment a JOIN FETCH a.house h JOIN FETCH h.street WHERE a.id = :id")
    Optional<Apartment> findById(@Param("id") int id);

    List<Apartment> findByHouseIdIn(List<Integer> ids);

    @Modifying
    @Query(value = "DELETE FROM Apartments a WHERE a.id IN :ids", nativeQuery = true)
    int deleteByIdIn(@Param("ids") List<Integer> ids);
}
