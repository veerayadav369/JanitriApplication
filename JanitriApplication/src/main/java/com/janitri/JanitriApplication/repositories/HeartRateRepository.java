package com.janitri.JanitriApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.janitri.JanitriApplication.models.HeartRate;

public interface HeartRateRepository extends JpaRepository<HeartRate, Long> {

    // Custom query to find heart rates by patient ID
    List<HeartRate> findByPatient_Id(Long patientId); // Ensure this method is here
}
