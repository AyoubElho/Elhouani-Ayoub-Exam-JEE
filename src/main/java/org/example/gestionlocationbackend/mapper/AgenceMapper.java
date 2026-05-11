package org.example.gestionlocationbackend.mapper;

import org.example.gestionlocationbackend.dtos.AgenceDTO;
import org.example.gestionlocationbackend.entity.Agence;

public class AgenceMapper {

    public static AgenceDTO fromAgence(
            Agence agence
    ) {

        AgenceDTO dto = new AgenceDTO();

        dto.setId(agence.getId());
        dto.setNom(agence.getNom());
        dto.setAdresse(agence.getAdresse());
        dto.setVille(agence.getVille());
        dto.setTelephone(agence.getTelephone());

        return dto;
    }

    public static Agence fromAgenceDTO(
            AgenceDTO dto
    ) {

        Agence agence = new Agence();

        agence.setId(dto.getId());
        agence.setNom(dto.getNom());
        agence.setAdresse(dto.getAdresse());
        agence.setVille(dto.getVille());
        agence.setTelephone(dto.getTelephone());

        return agence;
    }
}