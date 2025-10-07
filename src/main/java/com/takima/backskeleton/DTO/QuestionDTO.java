package com.takima.backskeleton.DTO;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;

    @NotBlank(message = "Le texte de la question est obligatoire")
    private String texteQuestion;

    @NotNull(message = "L'identifiant du sondage est obligatoire")
    private Long sondageId;

    private List<ReponseOptionDTO> options;
}
