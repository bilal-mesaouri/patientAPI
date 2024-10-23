package com.patientService.model;

import org.springframework.data.annotation.Id;
import java.util.List;

public class FeedbackForm {
    @Id
    private String id;
    private String patientId; 
    private String feelings; 
    private List<String> symptoms;

    // Constructor
    public FeedbackForm(String patientId, String feelings, List<String> symptoms) {
        this.patientId = patientId;
        this.feelings = feelings;
        this.symptoms = symptoms;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
