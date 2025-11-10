package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.QuestionRepository;
import com.takima.backskeleton.DAO.ReponseOptionRepository;
import com.takima.backskeleton.DAO.SondageRepository;
import com.takima.backskeleton.DTO.QuestionDTO;
import com.takima.backskeleton.DTO.ReponseOptionDTO;
import com.takima.backskeleton.exceptions.ResourceNotFoundException;
import com.takima.backskeleton.models.Question;
import com.takima.backskeleton.models.ReponseOption;
import com.takima.backskeleton.models.Sondage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private ReponseOptionRepository reponseOptionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findByIdWithOptions(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question non trouvée avec l'id: " + id));
        return modelMapper.map(question, QuestionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> getQuestionsBySondageId(Long sondageId) {
        return questionRepository.findAllBySondageIdWithOptions(sondageId)
                .stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuestionDTO addQuestionToSondage(Long sondageId, QuestionDTO questionDTO) {
        Sondage sondage = sondageRepository.findById(sondageId)
                .orElseThrow(() -> new ResourceNotFoundException("Sondage non trouvé avec l'id: " + sondageId));

        Question question = modelMapper.map(questionDTO, Question.class);
        question.setSondage(sondage);
        question = questionRepository.save(question);

        return modelMapper.map(question, QuestionDTO.class);
    }

    @Override
    @Transactional
    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question non trouvée avec l'id: " + id);
        }

        Question question = modelMapper.map(questionDTO, Question.class);
        question.setId(id);
        question = questionRepository.save(question);

        return modelMapper.map(question, QuestionDTO.class);
    }

    @Override
    @Transactional
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question non trouvée avec l'id: " + id);
        }
        questionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ReponseOptionDTO addOptionToQuestion(Long questionId, ReponseOptionDTO optionDTO) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question non trouvée avec l'id: " + questionId));

        // Création manuelle de l'entité pour éviter que ModelMapper ne tente de mapper des relations
        ReponseOption option = new ReponseOption();
        option.setTexteOption(optionDTO.getTexteOption());
        option.setQuestion(question);

        // Sauvegarde de l'option
        option = reponseOptionRepository.save(option);

        // Retourner le DTO mappé depuis l'entité sauvegardée
        return modelMapper.map(option, ReponseOptionDTO.class);
    }
}
