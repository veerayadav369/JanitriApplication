package com.janitri.JanitriApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.janitri.JanitriApplication.models.Patient;
import com.janitri.JanitriApplication.repositories.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getPatientsByUserId(long userId) {
        return patientRepository.findByUserId(userId);
    }

    // Add this method to get a patient by their ID
    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findById(patientId);
    }
}
