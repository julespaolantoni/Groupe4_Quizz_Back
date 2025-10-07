package com.takima.backskeleton.services;

import com.takima.backskeleton.DTO.SondageDTO;
import java.util.List;

public interface SondageService {
    List<SondageDTO> getAllSondages();
    SondageDTO getSondageById(Long id);
    SondageDTO createSondage(SondageDTO sondageDTO);
    SondageDTO updateSondage(Long id, SondageDTO sondageDTO);
    void deleteSondage(Long id);
}
