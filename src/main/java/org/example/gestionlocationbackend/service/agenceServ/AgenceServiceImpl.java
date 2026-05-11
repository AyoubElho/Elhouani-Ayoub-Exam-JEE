package org.example.gestionlocationbackend.service.agenceServ;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.dtos.AgenceDTO;
import org.example.gestionlocationbackend.entity.Agence;
import org.example.gestionlocationbackend.mapper.AgenceMapper;
import org.example.gestionlocationbackend.repository.AgenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgenceServiceImpl
        implements AgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    public AgenceDTO saveAgence(
            AgenceDTO dto
    ) {

        Agence agence =
                AgenceMapper.fromAgenceDTO(dto);

        Agence savedAgence =
                agenceRepository.save(agence);

        return AgenceMapper.fromAgence(
                savedAgence
        );
    }

    @Override
    public List<AgenceDTO> getAllAgences() {

        return agenceRepository.findAll()
                .stream()
                .map(AgenceMapper::fromAgence)
                .toList();
    }

    @Override
    public AgenceDTO getAgenceById(Long id) {

        Agence agence = agenceRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Agence not found"
                        ));

        return AgenceMapper.fromAgence(
                agence
        );
    }

    @Override
    public void deleteAgence(Long id) {

        agenceRepository.deleteById(id);
    }
}