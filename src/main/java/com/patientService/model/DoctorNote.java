package com.patientService.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
@Document(collection = "doctor_notes")
public class DoctorNote {

    @Id
    private String id;
    private String patientId;
    private String note;
    private Date createdAt;

    // Add getters and setters
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date date) {
         createdAt = date;
    }
    // Constructors
    public DoctorNote() {
    }

    public DoctorNote(String patientId, String note) {
        this.patientId = patientId;
        this.note = note;
        createdAt = new Date(); 
    }

    // Getters and Setters
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
