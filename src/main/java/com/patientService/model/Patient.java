package com.patientService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.patientService.enums.SeverityLevel;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Document(collection = "patients")
public class Patient {

    @Id
    private String id; // MongoDB uses String as ID type
    private String name;
    private Date birthDate;
    private SeverityLevel severity; 
    private Doctor primaryDoctor;
    private EmergencyContact emergencyContact;
    // Getters and setters
    public void setDoctor(Doctor doctor){
        // this.doctor = doctor;
    }
    public void setNurse(Nurse nurse){
        // this.nurse = nurse;
    }

    // Measurements to be removed after, replaced by a call to the DB when needed
    public Patient(String name, Date birthDate, SeverityLevel severity,
                Doctor primaryDoctor, EmergencyContact emergencyContact) {
        
        this.name = name;
        this.birthDate = birthDate;
        this.severity = severity;
        this.primaryDoctor = primaryDoctor;
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

    public Doctor getPrimaryDoctor(){
        return this.primaryDoctor;
    }

    public int calculateAge() {

        LocalDate localBirthDate = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDate currentDate = LocalDate.now();
        
        if ((this.birthDate != null) && (localBirthDate != null)) {
            return Period.between(localBirthDate, currentDate).getYears();
        } else {
            throw new IllegalArgumentException("The birthDate cannot be null");
        }
    }
}