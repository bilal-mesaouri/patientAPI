package com.patientService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patientService.enums.SeverityLevel;
import com.patientService.model.EmergencyContact;
import com.patientService.model.Patient;
import com.patientService.service.PatientService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @PostMapping("/{id}/update-severity")
    public ResponseEntity<Patient> updateSeverity(@PathVariable String id, @RequestBody SeverityLevel severityLevel) {
        // Use the service to update severity
        Patient updatedPatient = patientService.updateSeverity(id, severityLevel);
        
        // Return the updated patient entity
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/{id}")
    public Patient getPatientDetails(@PathVariable String id) {
        return patientService.getPatientDetails(id);
    }

    @GetMapping()
    public List<Patient> getPatientDetails() {
        return patientService.getPatients();
    }

    @PostMapping("/save")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping("/{id}/report")
    public String getReport(@PathVariable String id) {
        
        Patient patient = patientService.getPatientDetails(id);
        //the following code should be used to fethc DATA API to get the latest measurments 
        
        // String apiUrl = "https://example.com/api/report/" + id;
        // RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> reportData = new HashMap<>();
        
        // try {
        //     // Parse the API response as a JSON object
        //     reportData = mapper.readValue(response.getBody(), Map.class);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        reportData.put("specialite", patient.getPrimaryDoctor().getSpecialization());
        reportData.put("medcin traitant", patient.getPrimaryDoctor().getName());
        reportData.put("age", patient.calculateAge());


        String updatedJsonResponse = "";
        try {
            updatedJsonResponse = mapper.writeValueAsString(reportData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedJsonResponse;
    }
}
