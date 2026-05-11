package org.example.gestionlocationbackend.web;

import org.example.gestionlocationbackend.dtos.MotoDTO;
import org.example.gestionlocationbackend.dtos.VehiculeDTO;
import org.example.gestionlocationbackend.dtos.VoitureDTO;
import org.example.gestionlocationbackend.service.vehiculeServ.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@CrossOrigin("*")
public class VehiculeRestController {
    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping
    public List<VehiculeDTO> getVehicules() {

        return vehiculeService.getAllVehicules();
    }

    @PostMapping("/voitures")
    public VoitureDTO saveVoiture(
            @RequestBody VoitureDTO dto
    ){

        return vehiculeService
                .saveVoiture(dto);
    }

    @PostMapping("/motos")
    public MotoDTO saveMoto(
            @RequestBody MotoDTO dto
    ){

        return vehiculeService
                .saveMoto(dto);
    }


    @GetMapping("/{id}")
    public VehiculeDTO getVehicule(
            @PathVariable Long id
    ) {

        return vehiculeService.getVehiculeById(id);
    }

    @GetMapping("/disponibles")
    public List<VehiculeDTO>
    getVehiculesDisponibles() {

        return vehiculeService
                .getVehiculesDisponibles();
    }

    @DeleteMapping("/{id}")
    public void deleteVehicule(
            @PathVariable Long id
    ) {

        vehiculeService.deleteVehicule(id);
    }
}