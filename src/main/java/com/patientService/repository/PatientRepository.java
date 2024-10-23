package com.patientService.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.patientService.enums.SeverityLevel;
import com.patientService.model.EmergencyContact;
import com.patientService.model.Patient;

import java.util.*;

public interface PatientRepository extends MongoRepository<Patient, String> {
    
    List<Patient> findAllByOrderBySeverityDesc();
    List<Patient> findAllBySeverity(SeverityLevel severityLevel);
    
}
