package com.patientService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.patientService.model.FeedbackForm;

import java.util.List;

public interface FeedbackFormRepository extends MongoRepository<FeedbackForm, String> {
    List<FeedbackForm> findByPatientId(String patientId);
}