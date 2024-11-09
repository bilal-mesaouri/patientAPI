package com.patientService.model;

public class EmergencyContact {
    String Name;
    String Email;

    public EmergencyContact(String name, String email){
        Name=name;
        Email=email;
    }

    @Override
    public String toString() {
        return "Entity {" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }

}
