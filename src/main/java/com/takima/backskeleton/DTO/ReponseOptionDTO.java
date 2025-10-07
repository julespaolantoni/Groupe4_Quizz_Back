package com.takima.backskeleton.DTO;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ReponseOptionDTO {
    private Long id;

    @NotBlank(message = "Le texte de l'option est obligatoire")
    private String texteOption;

    @NotNull(message = "L'identifiant de la question est obligatoire")
    private Long questionId;

    private ResultatDTO resultat;
}
