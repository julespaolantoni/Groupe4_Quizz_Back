package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.SondageRepository;
import com.takima.backskeleton.DTO.QuestionDTO;
import com.takima.backskeleton.DTO.ReponseOptionDTO;
import com.takima.backskeleton.DTO.SondageDTO;
import com.takima.backskeleton.exceptions.ResourceNotFoundException;
import com.takima.backskeleton.models.Question;
import com.takima.backskeleton.models.Sondage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SondageServiceImpl implements SondageService {

    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Récupère tous les sondages avec leurs questions (et force le chargement des options)
     */
    @Override
    @Transactional(readOnly = true)
    public List<SondageDTO> getAllSondages() {
        List<Sondage> sondages = sondageRepository.findAllWithQuestions();

        // Forcer le chargement des options pour éviter LazyInitializationException
        sondages.forEach(s -> s.getQuestions().forEach(q -> q.getOptions().size()));

        return sondages.stream()
                .map(this::mapSondageWithDetails)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un sondage par son ID
     */
    @Override
    @Transactional(readOnly = true)
    public SondageDTO getSondageById(Long id) {
        Sondage sondage = sondageRepository.findByIdWithQuestions(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sondage non trouvé avec l'id: " + id));

        // Charger les options associées
        sondage.getQuestions().forEach(q -> q.getOptions().size());

        return mapSondageWithDetails(sondage);
    }

    /**
     * Crée un nouveau sondage
     */
    @Override
    @Transactional
    public SondageDTO createSondage(SondageDTO sondageDTO) {
        Sondage sondage = modelMapper.map(sondageDTO, Sondage.class);
        sondage.setDateCreation(LocalDate.now());
        sondage = sondageRepository.save(sondage);
        return mapSondageWithDetails(sondage);
    }

    /**
     * Met à jour un sondage existant
     */
    @Override
    @Transactional
    public SondageDTO updateSondage(Long id, SondageDTO sondageDTO) {
        if (!sondageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sondage non trouvé avec l'id: " + id);
        }
        Sondage sondage = modelMapper.map(sondageDTO, Sondage.class);
        sondage.setId(id);
        sondage = sondageRepository.save(sondage);
        return mapSondageWithDetails(sondage);
    }

    /**
     * Supprime un sondage par ID
     */
    @Override
    @Transactional
    public void deleteSondage(Long id) {
        if (!sondageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sondage non trouvé avec l'id: " + id);
        }
        sondageRepository.deleteById(id);
    }

    /**
     * Convertit un Sondage en SondageDTO, en mappant aussi ses questions et options.
     */
    private SondageDTO mapSondageWithDetails(Sondage sondage) {
        SondageDTO dto = modelMapper.map(sondage, SondageDTO.class);

        List<QuestionDTO> questions = sondage.getQuestions().stream()
                .map(q -> {
                    QuestionDTO qdto = modelMapper.map(q, QuestionDTO.class);
                    qdto.setOptions(q.getOptions().stream()
                            .map(o -> modelMapper.map(o, ReponseOptionDTO.class))
                            .collect(Collectors.toList()));
                    return qdto;
                }).collect(Collectors.toList());

        dto.setQuestions(questions);
        return dto;
    }
}
