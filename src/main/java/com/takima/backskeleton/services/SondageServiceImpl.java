package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.SondageRepository;
import com.takima.backskeleton.DTO.SondageDTO;
import com.takima.backskeleton.exceptions.ResourceNotFoundException;
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

    @Override
    @Transactional(readOnly = true)
    public List<SondageDTO> getAllSondages() {
        return sondageRepository.findAllWithDetails()
                .stream()
                .map(sondage -> modelMapper.map(sondage, SondageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SondageDTO getSondageById(Long id) {
        Sondage sondage = sondageRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sondage non trouvé avec l'id: " + id));
        return modelMapper.map(sondage, SondageDTO.class);
    }

    @Override
    @Transactional
    public SondageDTO createSondage(SondageDTO sondageDTO) {
        Sondage sondage = modelMapper.map(sondageDTO, Sondage.class);
        sondage.setDateCreation(LocalDate.now());
        sondage = sondageRepository.save(sondage);
        return modelMapper.map(sondage, SondageDTO.class);
    }

    @Override
    @Transactional
    public SondageDTO updateSondage(Long id, SondageDTO sondageDTO) {
        if (!sondageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sondage non trouvé avec l'id: " + id);
        }

        Sondage sondage = modelMapper.map(sondageDTO, Sondage.class);
        sondage.setId(id);
        sondage = sondageRepository.save(sondage);
        return modelMapper.map(sondage, SondageDTO.class);
    }

    @Override
    @Transactional
    public void deleteSondage(Long id) {
        if (!sondageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sondage non trouvé avec l'id: " + id);
        }
        sondageRepository.deleteById(id);
    }
}
