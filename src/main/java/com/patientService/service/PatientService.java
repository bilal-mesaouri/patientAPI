package com.patientService.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientService.enums.SeverityLevel;
import com.patientService.exception.ResourceNotFoundException;
import com.patientService.model.EmergencyContact;
import com.patientService.model.Patient;
import com.patientService.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public Patient getPatientDetails(String patientId) {
        return patientRepository.findById(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getAllPatientsBySeverity(SeverityLevel severtyLevel){
        return patientRepository.findAllBySeverity(severtyLevel);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public EmergencyContact getEmergencyContact(String Id){
        Patient patient = patientRepository.findById(Id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return patient.getEmergencyContact();
    }

    public Patient updateEmergencyContact(String patientId, EmergencyContact emergencyContact) {

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            
            patient.setEmergencyContact(emergencyContact);
            
            return patientRepository.save(patient);
        } else {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
    }

    public String alertEmergencyContact(String patientId){
        return "fetched Data";
    }

    public Patient updateSeverity(String id, SeverityLevel severityLevel) {
        // Find the patient by ID
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
        
        // Update the severity level
        patient.setSeverityLevel(severityLevel);
        
        // Save and return the updated patient entity
        return patientRepository.save(patient);
    }


    
}
