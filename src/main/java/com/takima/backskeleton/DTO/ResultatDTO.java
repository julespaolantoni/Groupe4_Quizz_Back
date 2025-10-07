package com.takima.backskeleton.DTO;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class ResultatDTO {
    private Long id;

    @NotNull(message = "L'identifiant de l'option est obligatoire")
    private Long reponseId;

    @Min(value = 0, message = "Le nombre de votes ne peut pas être négatif")
    private Integer nombreVotes = 0;
}
