package com.payriff.security_ms.repository;

import com.payriff.security_ms.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    Optional<UserCredential> findByUsername(String username);

    Optional<UserCredential> findByEmail(String email);

}
