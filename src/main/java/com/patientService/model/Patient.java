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
        StringBuilder sb = new StringBuilder();
        sb.append("Entity {");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", severity=").append(severity);
        sb.append(", primaryDoctor=").append(primaryDoctor);
        sb.append(", emergencyContact=").append(emergencyContact);
        sb.append('}');
        return sb.toString();
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

    public String getName() {
        return name;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }
    
    public SeverityLevel getSeverity() {
        return severity;
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

    public void setSeverityLevel(SeverityLevel severityLevel){
        this.severity = severityLevel;
    }
}