package org.example.gestionlocationbackend.web;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.dtos.VehiculeDTO;
import org.example.gestionlocationbackend.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@CrossOrigin("*")
public class VehiculeRestController {
    @Autowired
    private VehiculeService vehiculeService;

    // GET ALL
    @GetMapping
    public List<VehiculeDTO> getVehicules() {

        return vehiculeService.getAllVehicules();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public VehiculeDTO getVehicule(
            @PathVariable Long id
    ) {

        return vehiculeService.getVehiculeById(id);
    }

    // GET DISPONIBLES
    @GetMapping("/disponibles")
    public List<VehiculeDTO>
    getVehiculesDisponibles() {

        return vehiculeService
                .getVehiculesDisponibles();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteVehicule(
            @PathVariable Long id
    ) {

        vehiculeService.deleteVehicule(id);
    }
}