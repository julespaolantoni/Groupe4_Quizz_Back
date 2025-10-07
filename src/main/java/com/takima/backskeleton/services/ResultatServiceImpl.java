package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.ReponseOptionRepository;
import com.takima.backskeleton.DAO.ResultatRepository;
import com.takima.backskeleton.DTO.ResultatDTO;
import com.takima.backskeleton.exceptions.ResourceNotFoundException;
import com.takima.backskeleton.models.ReponseOption;
import com.takima.backskeleton.models.Resultat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service  // Cette annotation est cruciale pour que Spring crée le bean
public class ResultatServiceImpl implements ResultatService {

    private final ResultatRepository resultatRepository;
    private final ReponseOptionRepository reponseOptionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultatServiceImpl(ResultatRepository resultatRepository,
                             ReponseOptionRepository reponseOptionRepository,
                             ModelMapper modelMapper) {
        this.resultatRepository = resultatRepository;
        this.reponseOptionRepository = reponseOptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ResultatDTO incrementerVotes(Long reponseId) {
        ReponseOption option = reponseOptionRepository.findById(reponseId)
                .orElseThrow(() -> new ResourceNotFoundException("Option de réponse non trouvée avec l'id: " + reponseId));

        Resultat resultat = option.getResultat();
        if (resultat == null) {
            resultat = new Resultat();
            resultat.setReponseOption(option);
            resultat.setNombreVotes(1);
        } else {
            resultat.setNombreVotes(resultat.getNombreVotes() + 1);
        }

        resultat = resultatRepository.save(resultat);
        return modelMapper.map(resultat, ResultatDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ResultatDTO getResultat(Long reponseId) {
        ReponseOption option = reponseOptionRepository.findById(reponseId)
                .orElseThrow(() -> new ResourceNotFoundException("Option de réponse non trouvée avec l'id: " + reponseId));

        Resultat resultat = option.getResultat();
        if (resultat == null) {
            ResultatDTO dto = new ResultatDTO();
            dto.setReponseId(reponseId);
            dto.setNombreVotes(0);
            return dto;
        }
        return modelMapper.map(resultat, ResultatDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatDTO> getResultatsParQuestion(Long questionId) {
        List<ReponseOption> options = reponseOptionRepository.findAllByQuestionId(questionId);
        return options.stream()
                .map(option -> getResultat(option.getId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatDTO> getResultatsParSondage(Long sondageId) {
        List<ReponseOption> options = reponseOptionRepository.findAllBySondageId(sondageId);
        return options.stream()
                .map(option -> getResultat(option.getId()))
                .collect(Collectors.toList());
    }
}
