package com.example.soba.repository;

import com.example.soba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean usernameExists(String username);
    boolean emailExists(String email);

    //why are there optionals
}
