package com.takima.backskeleton.controllers;

import com.takima.backskeleton.DTO.ResultatDTO;
import com.takima.backskeleton.services.ResultatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
@CrossOrigin(origins = "*")
@Tag(name = "Résultats", description = "API de gestion des résultats et des votes")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @Operation(summary = "Voter pour une option")
    @PostMapping("/reponses/{reponseId}/vote")
    public ResponseEntity<ResultatDTO> voter(@PathVariable Long reponseId) {
        return ResponseEntity.ok(resultatService.incrementerVotes(reponseId));
    }

    @Operation(summary = "Obtenir les résultats d'un sondage")
    @GetMapping("/sondages/{sondageId}")
    public ResponseEntity<List<ResultatDTO>> getResultatsSondage(@PathVariable Long sondageId) {
        return ResponseEntity.ok(resultatService.getResultatsParSondage(sondageId));
    }

    @Operation(summary = "Obtenir les résultats d'une question")
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<List<ResultatDTO>> getResultatsQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(resultatService.getResultatsParQuestion(questionId));
    }
}
