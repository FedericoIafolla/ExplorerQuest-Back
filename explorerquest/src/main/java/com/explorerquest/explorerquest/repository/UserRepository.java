package com.explorerquest.explorerquest.repository;

import com.explorerquest.explorerquest.entity.User; // Assicurati che l'entity User sia importata
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
