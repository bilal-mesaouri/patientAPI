package com.patientService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.patientService.model.DoctorNote;

public interface DoctorNoteRepository extends MongoRepository<DoctorNote, String> {
    // Additional query methods can be defined here if needed
        List<DoctorNote> findByPatientId(String patientId);
}