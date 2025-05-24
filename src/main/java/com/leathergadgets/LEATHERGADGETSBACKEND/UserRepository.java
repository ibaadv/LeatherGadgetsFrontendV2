package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Long> {
    Optional<user> findByEmail(String email);
}