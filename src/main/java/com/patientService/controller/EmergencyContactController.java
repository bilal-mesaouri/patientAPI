package com.patientService.controller;

import com.patientService.model.EmergencyContact;
import com.patientService.model.Patient;
import com.patientService.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour la gestion des contacts d'urgence des patients.
 */
@RestController
@RequestMapping("/api/patients")
@Tag(name = "Emergency Contact Management", description = "Gestion des contacts d'urgence pour les patients")
public class EmergencyContactController {

    @Autowired
    private PatientService patientService;

    /**
     * Récupère les détails du contact d'urgence d'un patient.
     *
     * @param id ID du patient.
     * @return Détails du contact d'urgence.
     */
    @GetMapping("/emergency-contact/{id}")
    @Operation(summary = "Get Emergency Contact",
            description = "Récupère les informations du contact d'urgence d'un patient par ID.")
    public EmergencyContact getEmergencyContact(
            @Parameter(description = "ID du patient", example = "123")
            @PathVariable String id) {
        return patientService.getEmergencyContact(id);
    }

    /**
     * Met à jour le contact d'urgence d'un patient.
     *
     * @param patientId ID du patient.
     * @param emergencyContact Détails mis à jour du contact d'urgence.
     * @return Informations mises à jour du patient.
     */
    @PostMapping("/{patientId}/update-emergency-contact")
    @Operation(summary = "Update Emergency Contact",
            description = "Met à jour les informations du contact d'urgence pour un patient.")
    public Patient updateEmergencyContact(
            @Parameter(description = "ID du patient", example = "123")
            @PathVariable String patientId,
            @Parameter(description = "Nouveau contact d'urgence", required = true)
            @RequestBody EmergencyContact emergencyContact) {
        return patientService.updateEmergencyContact(patientId, emergencyContact);
    }

    /**
     * Envoie une alerte au contact d'urgence d'un patient.
     *
     * @param id ID du patient.
     * @return Message confirmant l'envoi de l'alerte.
     */
    @GetMapping("/alert-emergency-contact/{id}")
    @Operation(summary = "Alert Emergency Contact",
            description = "Envoie une alerte au contact d'urgence d'un patient.")
    public String alertEmergencyContact(
            @Parameter(description = "ID du patient", example = "123")
            @PathVariable String id) {
        return patientService.alertEmergencyContact(id);
    }
}
