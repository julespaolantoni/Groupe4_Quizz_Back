package com.takima.backskeleton.DTO;

import com.takima.backskeleton.models.Categorie;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class SondageDTO {
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotNull(message = "La catégorie est obligatoire")
    private Categorie categorie;

    private String description;
    private LocalDate dateCreation;
    private List<QuestionDTO> questions;
}
