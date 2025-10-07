package com.takima.backskeleton.services;

import com.takima.backskeleton.DTO.QuestionDTO;
import com.takima.backskeleton.DTO.ReponseOptionDTO;
import java.util.List;

public interface QuestionService {
    QuestionDTO getQuestionById(Long id);
    List<QuestionDTO> getQuestionsBySondageId(Long sondageId);
    QuestionDTO addQuestionToSondage(Long sondageId, QuestionDTO questionDTO);
    QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO);
    void deleteQuestion(Long id);
    ReponseOptionDTO addOptionToQuestion(Long questionId, ReponseOptionDTO optionDTO);
}
