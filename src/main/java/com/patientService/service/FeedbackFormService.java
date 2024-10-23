package com.patientService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientService.model.FeedbackForm;
import com.patientService.repository.FeedbackFormRepository;

import java.util.Optional;
import java.util.List;

@Service
public class FeedbackFormService {

    @Autowired
    private FeedbackFormRepository feedbackFormRepository;

    public FeedbackForm saveForm(FeedbackForm form) {
        return feedbackFormRepository.save(form);
    }

    public List<FeedbackForm> getFormsByPatientId(String patientId) {
        return feedbackFormRepository.findByPatientId(patientId);
    }

    public Optional<FeedbackForm> getFormById(String id) {
        return feedbackFormRepository.findById(id);
    }
}
