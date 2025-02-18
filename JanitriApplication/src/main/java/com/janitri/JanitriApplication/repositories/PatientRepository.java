package com.janitri.JanitriApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.janitri.JanitriApplication.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByUserId(Long userId);
}
