package com.janitri.JanitriApplication.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.janitri.JanitriApplication.models.Patient;
import com.janitri.JanitriApplication.models.User;
import com.janitri.JanitriApplication.services.PatientService;
import com.janitri.JanitriApplication.services.UserService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;  // Declare UserService

    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;  // Initialize UserService
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        // Check if the userId is passed with the patient
        if (patient.getUser() == null || patient.getUser().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("UserId cannot be null");
        }

        // Fetch the user based on the provided userId
        User user = userService.findById(patient.getUser().getId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("User not found");
        }

        // Set the user in the patient entity before saving
        patient.setUser(user);

        // Save the patient
        Patient createdPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<Patient>> getPatients(@PathVariable long userId) {
        List<Patient> patients = patientService.getPatientsByUserId(userId);
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patients);
    }
}
