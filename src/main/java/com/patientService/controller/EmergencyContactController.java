package com.patientService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientService.model.EmergencyContact;
import com.patientService.model.Patient;
import com.patientService.service.PatientService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/patients")
public class EmergencyContactController {

    @Autowired
    PatientService patientService;

    @GetMapping("/emergency-contact/{id}")
    public EmergencyContact getEmergencyContact(@PathVariable String id) {
        return patientService.getEmergencyContact(id);
    }

    @PostMapping("/{patientId}/update-emergency-contact")
    public Patient updateEmergencyContact(@PathVariable String patientId, 
                                          @RequestBody EmergencyContact emergencyContact) {
    
        return patientService.updateEmergencyContact(patientId, emergencyContact);
    }

    @GetMapping("/alert-emergency-contact/{id}")
    public String getMethodName(@PathVariable String id) {
        return patientService.alertEmergencyContact(id);
    }
    
}