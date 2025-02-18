package com.janitri.JanitriApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.janitri.JanitriApplication.controllers.HeartRateController;
import com.janitri.JanitriApplication.models.HeartRate;
import com.janitri.JanitriApplication.services.HeartRateService;

class HeartRateControllerTests {

    @Mock
    private HeartRateService heartRateService;

    @InjectMocks
    private HeartRateController heartRateController;

    HeartRateControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRecordHeartRate() {
        HeartRate heartRate = new HeartRate();
        heartRate.setHeartRate(75);
        heartRate.setTimestamp(LocalDateTime.now());

        when(heartRateService.addHeartRate(heartRate)).thenReturn(heartRate);

        ResponseEntity<HeartRate> response = heartRateController.addHeartRate(null, heartRate);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(heartRate.getHeartRate(), response.getBody().getHeartRate());
        verify(heartRateService, times(1)).addHeartRate(heartRate);
    }

    @Test
    void testRetrieveHeartRates() {
        long patientId = 1L;
        List<HeartRate> heartRates = List.of(new HeartRate(patientId, 75, LocalDateTime.now(), null)); // Use List instead of Optional

        // Return the list of heart rates from the service
        when(heartRateService.getHeartRatesByPatientId(patientId)).thenReturn(heartRates);

        // Ensure the controller method handles the list properly
        ResponseEntity<List<HeartRate>> response = heartRateController.getHeartRates(patientId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());  // Check size of the list
        verify(heartRateService, times(1)).getHeartRatesByPatientId(patientId);
    }
}