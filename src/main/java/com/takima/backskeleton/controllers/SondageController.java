package com.takima.backskeleton.controllers;

import com.takima.backskeleton.DTO.SondageDTO;
import com.takima.backskeleton.services.SondageService;
import com.takima.backskeleton.services.ResultatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sondages")
@CrossOrigin(origins = "*")
@Tag(name = "Sondages", description = "API de gestion des sondages Disney")
public class SondageController {

    @Autowired
    private SondageService sondageService;

    @Operation(summary = "Récupérer tous les sondages")
    @GetMapping
    public ResponseEntity<List<SondageDTO>> getAllSondages() {
        return ResponseEntity.ok(sondageService.getAllSondages());
    }

    @Operation(summary = "Récupérer un sondage par son ID")
    @ApiResponse(responseCode = "200", description = "Sondage trouvé")
    @ApiResponse(responseCode = "404", description = "Sondage non trouvé")
    @GetMapping("/{id}")
    public ResponseEntity<SondageDTO> getSondage(@PathVariable Long id) {
        return ResponseEntity.ok(sondageService.getSondageById(id));
    }

    @Operation(summary = "Créer un nouveau sondage")
    @PostMapping
    public ResponseEntity<SondageDTO> createSondage(@Valid @RequestBody SondageDTO sondageDTO) {
        return ResponseEntity.ok(sondageService.createSondage(sondageDTO));
    }

    @Operation(summary = "Mettre à jour un sondage")
    @PutMapping("/{id}")
    public ResponseEntity<SondageDTO> updateSondage(
            @PathVariable Long id,
            @Valid @RequestBody SondageDTO sondageDTO) {
        return ResponseEntity.ok(sondageService.updateSondage(id, sondageDTO));
    }

    @Operation(summary = "Supprimer un sondage")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSondage(@PathVariable Long id) {
        sondageService.deleteSondage(id);
        return ResponseEntity.ok().build();
    }
}
