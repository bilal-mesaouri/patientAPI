package com.patientService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.patientService.enums.SeverityLevel;

import java.util.*;

@Document(collection = "patients")
public class Patient {

    @Id
    private String id; // MongoDB uses String as ID type
    private String name;
    private int age;
    private SeverityLevel severity; 
    private List<Measurement> measurements; // evolution of patient metrics
    private Doctor doctor;
    private Nurse nurse;
    private EmergencyContact emergencyContact;
    // Getters and setters
    public void setDoctor(Doctor doctor){
        // this.doctor = doctor;
    }
    public void setNurse(Nurse nurse){
        // this.nurse = nurse;
    }

    // Measurements to be removed after, replaced by a call to the DB when needed
    public Patient(String name, int age, SeverityLevel severity,
                   List<Measurement> measurements, Doctor doctor, Nurse nurse, EmergencyContact emergencyContact) {
        
        this.name = name;
        this.age = age;
        this.severity = severity;
        this.measurements = measurements;
        this.doctor = doctor;
        this.nurse = nurse;
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public EmergencyContact getEmergencyContact(){
        return this.emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}