package org.example.gestionlocationbackend.service.vehiculeServ;


import org.example.gestionlocationbackend.dtos.MotoDTO;
import org.example.gestionlocationbackend.dtos.VehiculeDTO;
import org.example.gestionlocationbackend.dtos.VoitureDTO;

import java.util.List;

public interface VehiculeService {

    VoitureDTO saveVoiture(
            VoitureDTO dto
    );

    MotoDTO saveMoto(
            MotoDTO dto
    );
    List<VehiculeDTO> getAllVehicules();

    VehiculeDTO getVehiculeById(Long id);

    List<VehiculeDTO> getVehiculesDisponibles();

    void deleteVehicule(Long id);
}