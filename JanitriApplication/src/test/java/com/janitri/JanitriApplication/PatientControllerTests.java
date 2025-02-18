package com.janitri.JanitriApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.janitri.JanitriApplication.controllers.PatientController;
import com.janitri.JanitriApplication.models.Patient;
import com.janitri.JanitriApplication.services.PatientService;

class PatientControllerTests {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    PatientControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatient() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");

        when(patientService.addPatient(patient)).thenReturn(patient);

        ResponseEntity<Patient> response = (ResponseEntity<Patient>) patientController.addPatient(patient);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(patient.getName(), response.getBody().getName());
        verify(patientService, times(1)).addPatient(patient);
    }

    @Test
    void testGetPatientDetails() {
        long userId = 1L;
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");

        List<Patient> patients = Collections.singletonList(patient);
        when(patientService.getPatientsByUserId(userId)).thenReturn(patients);

        ResponseEntity<List<Patient>> response = patientController.getPatients(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(patient.getName(), response.getBody().get(0).getName());
        verify(patientService, times(1)).getPatientsByUserId(userId);
    }
}
