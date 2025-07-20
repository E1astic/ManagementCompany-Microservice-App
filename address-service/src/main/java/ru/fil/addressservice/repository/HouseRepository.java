package ru.fil.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fil.addressservice.model.entity.House;

import java.util.List;
import java.util.Optional;


@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    @Query("SELECT h FROM House h JOIN FETCH h.apartments a WHERE h.id = :id")
    Optional<House> findByIdWithApartments(@Param("id") Integer id);

    @Modifying
    @Query(value = "DELETE FROM Houses h WHERE h.id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") Integer id);

    @Modifying
    @Query(value = "DELETE FROM Houses h WHERE h.id IN :ids", nativeQuery = true)
    int deleteByIdIn(@Param("ids") List<Integer> ids);
}
