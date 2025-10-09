package com.example.auth_service.repository;

import com.example.auth_service.entity.MyAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyAppUserRepository extends JpaRepository<MyAppUser,Long> {
    Optional<MyAppUser> findByUsername(String username);

    MyAppUser findByEmail(String email);
}
