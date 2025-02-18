package com.janitri.JanitriApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.janitri.JanitriApplication.models.User;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);  // This method should accept a String (email)
}
