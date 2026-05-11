package org.example.gestionlocationbackend.service;


import org.example.gestionlocationbackend.dtos.VehiculeDTO;

import java.util.List;

public interface VehiculeService {

    VehiculeDTO saveVehicule(VehiculeDTO dto);

    List<VehiculeDTO> getAllVehicules();

    VehiculeDTO getVehiculeById(Long id);

    List<VehiculeDTO> getVehiculesDisponibles();

    void deleteVehicule(Long id);
}