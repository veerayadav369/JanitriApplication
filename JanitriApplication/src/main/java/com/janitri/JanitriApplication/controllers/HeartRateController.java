package com.janitri.JanitriApplication.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.janitri.JanitriApplication.models.HeartRate;
import com.janitri.JanitriApplication.models.Patient;
import com.janitri.JanitriApplication.services.HeartRateService;
import com.janitri.JanitriApplication.services.PatientService;

@RestController
@RequestMapping("/api/heart-rate")
public class HeartRateController {

    private final HeartRateService heartRateService;
    private final PatientService patientService;  // Injecting PatientService

    public HeartRateController(HeartRateService heartRateService, PatientService patientService) {
        this.heartRateService = heartRateService;
        this.patientService = patientService;
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<HeartRate> addHeartRate(@PathVariable Long patientId, @RequestBody HeartRate heartRate) {
        // Retrieve existing patient by ID
        Optional<Patient> patientOptional = patientService.getPatientById(patientId);

        if (!patientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // If patient not found, return 404
        }

        Patient patient = patientOptional.get(); // Get the patient object
        heartRate.setPatient(patient); // Associate the patient with the heart rate

        HeartRate recordedHeartRate = heartRateService.addHeartRate(heartRate); // Save the heart rate
        return ResponseEntity.ok(recordedHeartRate);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<HeartRate>> getHeartRates(@PathVariable Long patientId) {
        List<HeartRate> heartRates = heartRateService.getHeartRatesByPatientId(patientId); // Expecting a List
        if (heartRates.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no data found
        }
        return ResponseEntity.ok(heartRates); // Return the list of heart rates
    }
}
