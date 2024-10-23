package com.patientService.model;


public class Doctor {

    private String id;
    private String name;
    private String specialization;

    // Getters and setters

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName(){
        return this.name;
    }
    public String getSpecialization(){
        return this.specialization;
    }
}
