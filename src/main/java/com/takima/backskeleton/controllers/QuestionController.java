package com.takima.backskeleton.controllers;

import com.takima.backskeleton.DTO.QuestionDTO;
import com.takima.backskeleton.DTO.ReponseOptionDTO;
import com.takima.backskeleton.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
@Tag(name = "Questions", description = "API de gestion des questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Operation(summary = "Récupérer une question par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @Operation(summary = "Récupérer toutes les questions d'un sondage")
    @GetMapping("/sondage/{sondageId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsBySondage(@PathVariable Long sondageId) {
        return ResponseEntity.ok(questionService.getQuestionsBySondageId(sondageId));
    }

    @Operation(summary = "Ajouter une question à un sondage")
    @PostMapping("/sondage/{sondageId}")
    public ResponseEntity<QuestionDTO> addQuestion(
            @PathVariable Long sondageId,
            @Valid @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.addQuestionToSondage(sondageId, questionDTO));
    }

    @Operation(summary = "Mettre à jour une question")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(id, questionDTO));
    }

    @Operation(summary = "Supprimer une question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ajouter une option de réponse à une question")
    @PostMapping("/{id}/options")
    public ResponseEntity<ReponseOptionDTO> addOption(
            @PathVariable Long id,
            @Valid @RequestBody ReponseOptionDTO optionDTO) {
        return ResponseEntity.ok(questionService.addOptionToQuestion(id, optionDTO));
    }
}
