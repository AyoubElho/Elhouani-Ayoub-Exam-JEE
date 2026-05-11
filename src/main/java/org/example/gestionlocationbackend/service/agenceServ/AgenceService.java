package org.example.gestionlocationbackend.service.agenceServ;

import org.example.gestionlocationbackend.dtos.AgenceDTO;

import java.util.List;

public interface AgenceService {

    AgenceDTO saveAgence(AgenceDTO dto);

    List<AgenceDTO> getAllAgences();

    AgenceDTO getAgenceById(Long id);

    void deleteAgence(Long id);
}