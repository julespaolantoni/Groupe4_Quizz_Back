package com.takima.backskeleton.controllers;

import com.takima.backskeleton.DTO.ReponseOptionDTO;
import com.takima.backskeleton.DTO.ResultatDTO;
import com.takima.backskeleton.services.ResultatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reponses")
@CrossOrigin(origins = "*")
@Tag(name = "Réponses", description = "API de gestion des réponses et votes")
public class ReponseController {

    @Autowired
    private ResultatService resultatService;

    @Operation(summary = "Voter pour une option de réponse")
    @PostMapping("/{id}/vote")
    public ResponseEntity<ResultatDTO> voter(@PathVariable Long id) {
        return ResponseEntity.ok(resultatService.incrementerVotes(id));
    }
}
