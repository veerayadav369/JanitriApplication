package com.janitri.JanitriApplication.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
@AllArgsConstructor
public class HeartRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer heartRate;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HeartRate(Long id, Integer heartRate, LocalDateTime timestamp, Patient patient) {
		super();
		this.id = id;
		this.heartRate = heartRate;
		this.timestamp = timestamp;
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "HeartRate [id=" + id + ", heartRate=" + heartRate + ", timestamp=" + timestamp + ", patient=" + patient
				+ "]";
	}
	
    public HeartRate() {}
    
}
