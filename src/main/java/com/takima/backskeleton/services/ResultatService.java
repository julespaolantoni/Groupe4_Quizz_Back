package com.takima.backskeleton.services;

import com.takima.backskeleton.DTO.ResultatDTO;
import java.util.List;

public interface ResultatService {
    ResultatDTO incrementerVotes(Long reponseId);
    ResultatDTO getResultat(Long reponseId);
    List<ResultatDTO> getResultatsParQuestion(Long questionId);
    List<ResultatDTO> getResultatsParSondage(Long sondageId);
}
