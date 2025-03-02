package com.richard.linktreeClone.repositories;

import com.richard.linktreeClone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUrlUsername(String username);
    Optional<User> findByEmail (String email);
}
