package com.patientService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientService.enums.SeverityLevel;
import com.patientService.model.EmergencyContact;
import com.patientService.model.Patient;
import com.patientService.service.PatientService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/critical")
    public List<Patient> getAllPatientsBySeverity() {
        return patientService.getAllPatientsBySeverity(SeverityLevel.CRITICAL);
    }

    @GetMapping("/{id}")
    public Patient getPatientDetails(@PathVariable String id) {
        return patientService.getPatientDetails(id);
    }

    @PostMapping("/save")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

}
