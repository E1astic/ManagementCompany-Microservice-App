package ru.fil.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fil.authservice.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByIdIn(List<Integer> ids);

    Optional<User> findByEmail(String email);

    int deleteByApartmentIdIn(List<Integer> ids);
}
