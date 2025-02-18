package com.janitri.JanitriApplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.janitri.JanitriApplication.models.HeartRate;
import com.janitri.JanitriApplication.repositories.HeartRateRepository;

@Service
public class HeartRateService {
    private final HeartRateRepository heartRateRepository;

    public HeartRateService(HeartRateRepository heartRateRepository) {
        this.heartRateRepository = heartRateRepository;
    }

    // Adds a new heart rate
    public HeartRate addHeartRate(HeartRate heartRate) {
        return heartRateRepository.save(heartRate);
    }

    // Retrieves heart rates by patient ID
    public List<HeartRate> getHeartRatesByPatientId(Long patientId) {
        return heartRateRepository.findByPatient_Id(patientId); // Finds heart rates by the patient ID
    }
}
