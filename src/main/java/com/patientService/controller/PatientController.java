package com.patientService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patientService.enums.SeverityLevel;
import com.patientService.model.DoctorNote;
import com.patientService.model.Patient;
import com.patientService.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur pour la gestion des patients.
 */
@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient Management", description = "Gestion des opérations relatives aux patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * Récupère tous les patients avec un niveau de gravité CRITIQUE.
     *
     * @return Une liste de patients critiques.
     */
    @GetMapping("/critical")
    @Operation(summary = "Get all critical patients",
            description = "Récupère tous les patients dont le niveau de gravité est CRITIQUE.")
    public List<Patient> getAllPatientsBySeverity() {
        return patientService.getAllPatientsBySeverity(SeverityLevel.CRITICAL);
    }

    /**
     * Met à jour le niveau de gravité d'un patient.
     *
     * @param id ID du patient.
     * @param severityLevel Nouveau niveau de gravité.
     * @return Le patient mis à jour.
     */
    @PostMapping("/{id}/update-severity")
    @Operation(summary = "Update patient severity",
            description = "Met à jour le niveau de gravité d'un patient.")
    public ResponseEntity<Patient> updateSeverity(
            @Parameter(description = "ID du patient", example = "123") @PathVariable String id,
            @Parameter(description = "Nouveau niveau de gravité", required = true) @RequestBody SeverityLevel severityLevel) {
        Patient updatedPatient = patientService.updateSeverity(id, severityLevel);
        return ResponseEntity.ok(updatedPatient);
    }

    /**
     * Récupère les détails d'un patient spécifique.
     *
     * @param id ID du patient.
     * @return Les détails du patient.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get patient details",
            description = "Récupère les détails d'un patient en utilisant son ID.")
    public Patient getPatientDetails(
            @Parameter(description = "ID du patient", example = "123") @PathVariable String id) {
        return patientService.getPatientDetails(id);
    }

    /**
     * Récupère tous les patients.
     *
     * @return Une liste de tous les patients.
     */
    @GetMapping
    @Operation(summary = "Get all patients",
            description = "Récupère tous les patients enregistrés.")
    public List<Patient> getPatientDetails() {
        return patientService.getPatients();
    }

    /**
     * Enregistre un nouveau patient.
     *
     * @param patient Détails du patient à enregistrer.
     * @return Le patient enregistré.
     */
    @PostMapping("/save")
    @Operation(summary = "Save a new patient",
            description = "Enregistre un nouveau patient dans le système.")
    public Patient savePatient(
            @Parameter(description = "Détails du patient", required = true) @RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    /**
     * Génère un rapport pour un patient spécifique.
     *
     * @param id ID du patient.
     * @return Un rapport détaillé en format JSON.
     */
    @GetMapping("/{id}/report")
    @Operation(summary = "Get patient report",
            description = "Génère un rapport pour un patient spécifique avec ses détails et mesures.")
    public String getReport(
            @Parameter(description = "ID du patient", example = "123") @PathVariable String id) {
        Patient patient = patientService.getPatientDetails(id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> reportData = new HashMap<>();

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

    /**
     * Soumet une nouvelle note de médecin.
     *
     * @param doctorNote La note du médecin à soumettre.
     * @return La note soumise.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/doctor-note/submit")
    @Operation(summary = "Submit a doctor note",
            description = "Soumet une nouvelle note de médecin pour un patient.")
    public DoctorNote submitDoctorNote(
            @Parameter(description = "Note du médecin", required = true) @RequestBody DoctorNote doctorNote) {
        return patientService.submitDoctorNote(doctorNote);
    }

    /**
     * Récupère les notes d'un médecin pour un patient spécifique.
     *
     * @param id ID du patient.
     * @return Une liste de notes du médecin pour le patient.
     */
    @GetMapping("/doctor-note/{id}")
    @Operation(summary = "Get doctor notes by patient ID",
            description = "Récupère toutes les notes des médecins pour un patient spécifique.")
    public List<DoctorNote> getDoctorNotes(
            @Parameter(description = "ID du patient", example = "123") @PathVariable String id) {
        return patientService.getDoctorNoteByPatientId(id);
    }
}
