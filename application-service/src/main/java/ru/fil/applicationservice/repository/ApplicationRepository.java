package ru.fil.applicationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fil.applicationservice.model.entity.Application;
import ru.fil.common.enums.ApplicationStatus;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findByAuthorId(int authorId);

    @Modifying
    @Query("UPDATE Application a SET a.status = :newStatus WHERE a.id = :id")
    int updateStatusById(@Param("id") int id, @Param("newStatus") ApplicationStatus newStatus);
}
