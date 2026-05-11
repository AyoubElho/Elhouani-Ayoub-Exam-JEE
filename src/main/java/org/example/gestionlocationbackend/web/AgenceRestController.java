package org.example.gestionlocationbackend.web;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.dtos.AgenceDTO;
import org.example.gestionlocationbackend.service.agenceServ.AgenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AgenceRestController {

    private final AgenceService agenceService;

    @GetMapping
    public List<AgenceDTO> getAgences(){

        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    public AgenceDTO getAgence(
            @PathVariable Long id
    ){

        return agenceService.getAgenceById(id);
    }

    @PostMapping
    public AgenceDTO saveAgence(
            @RequestBody AgenceDTO dto
    ){

        return agenceService.saveAgence(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAgence(
            @PathVariable Long id
    ){

        agenceService.deleteAgence(id);
    }
}