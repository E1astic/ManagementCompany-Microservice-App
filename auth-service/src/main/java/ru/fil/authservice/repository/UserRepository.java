package ru.fil.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fil.authservice.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByIdIn(List<Integer> ids);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role.name = 'ROLE_ADMIN'")
    Optional<User> findAdmin();

    @Modifying
    @Query("UPDATE User u SET u.email = :newEmail WHERE u.role.name = 'ROLE_ADMIN'")
    void updateAdminEmail(@Param("newEmail") String newEmail);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id IN :ids")
    int deleteByApartmentIdIn(@Param("ids") List<Integer> ids);
}
