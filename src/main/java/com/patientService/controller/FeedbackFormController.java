package com.patientService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.patientService.model.FeedbackForm;
import com.patientService.service.FeedbackFormService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback-form")
public class FeedbackFormController {

    @Autowired
    private FeedbackFormService feedbackFormService;

    @PostMapping("/submit")
    public FeedbackForm submitForm(@RequestBody FeedbackForm form) {
        return feedbackFormService.saveForm(form);
    }

    @GetMapping("/patient/{patientId}")
    public List<FeedbackForm> getFormsByPatientId(@PathVariable String patientId) {
        return feedbackFormService.getFormsByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public Optional<FeedbackForm> getFormById(@PathVariable String id) {
        return feedbackFormService.getFormById(id);
    }
}
