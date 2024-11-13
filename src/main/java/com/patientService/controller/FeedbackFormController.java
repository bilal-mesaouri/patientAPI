package com.patientService.controller;

import com.patientService.model.FeedbackForm;
import com.patientService.service.FeedbackFormService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur pour la gestion des formulaires de retour des patients.
 */
@RestController
@RequestMapping("/feedback-form")
@Tag(name = "Feedback Form Management", description = "Gestion des formulaires de retour des patients")
public class FeedbackFormController {

    @Autowired
    private FeedbackFormService feedbackFormService;

    /**
     * Soumet un nouveau formulaire de retour.
     *
     * @param form Les détails du formulaire de retour.
     * @return Le formulaire de retour sauvegardé.
     */
    @PostMapping("/submit")
    @Operation(summary = "Submit Feedback Form",
            description = "Permet de soumettre un nouveau formulaire de retour.")
    public FeedbackForm submitForm(
            @Parameter(description = "Détails du formulaire de retour", required = true)
            @RequestBody FeedbackForm form) {
        return feedbackFormService.saveForm(form);
    }

    /**
     * Récupère les formulaires de retour associés à un patient spécifique.
     *
     * @param patientId ID du patient.
     * @return Une liste des formulaires de retour du patient.
     */
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get Feedback Forms by Patient ID",
            description = "Récupère tous les formulaires de retour soumis par un patient spécifique.")
    public List<FeedbackForm> getFormsByPatientId(
            @Parameter(description = "ID du patient", example = "123")
            @PathVariable String patientId) {
        return feedbackFormService.getFormsByPatientId(patientId);
    }

    /**
     * Récupère un formulaire de retour spécifique par son ID.
     *
     * @param id ID du formulaire de retour.
     * @return Le formulaire de retour correspondant.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get Feedback Form by ID",
            description = "Récupère un formulaire de retour spécifique en utilisant son ID.")
    public Optional<FeedbackForm> getFormById(
            @Parameter(description = "ID du formulaire de retour", example = "456")
            @PathVariable String id) {
        return feedbackFormService.getFormById(id);
    }
}
