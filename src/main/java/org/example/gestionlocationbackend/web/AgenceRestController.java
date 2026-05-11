package org.example.gestionlocationbackend.web;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.dtos.AgenceDTO;
import org.example.gestionlocationbackend.mapper.AgenceMapper;
import org.example.gestionlocationbackend.repository.AgenceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AgenceRestController {

    private final AgenceRepository agenceRepository;

    @GetMapping
    public List<AgenceDTO> getAgences(){

        return agenceRepository.findAll()
                .stream()
                .map(AgenceMapper::fromAgence)
                .toList();
    }
}