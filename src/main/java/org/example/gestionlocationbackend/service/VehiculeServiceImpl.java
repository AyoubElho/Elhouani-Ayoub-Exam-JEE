package org.example.gestionlocationbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.dtos.VehiculeDTO;
import org.example.gestionlocationbackend.entity.Vehicule;
import org.example.gestionlocationbackend.enumeartion.StatutVehicule;
import org.example.gestionlocationbackend.mapper.VehiculeMapper;
import org.example.gestionlocationbackend.repository.VehiculeRepository;
import org.example.gestionlocationbackend.service.VehiculeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehiculeServiceImpl
        implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public VehiculeDTO saveVehicule(
            VehiculeDTO dto
    ) {

        return dto;
    }

    @Override
    public List<VehiculeDTO> getAllVehicules() {

        return vehiculeRepository.findAll()
                .stream()
                .map(VehiculeMapper::fromVehicule)
                .toList();
    }

    @Override
    public VehiculeDTO getVehiculeById(Long id) {

        Vehicule vehicule = vehiculeRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vehicule not found"
                        ));

        return VehiculeMapper.fromVehicule(
                vehicule
        );
    }

    @Override
    public List<VehiculeDTO>
    getVehiculesDisponibles() {

        return vehiculeRepository.findAll()
                .stream()
                .filter(v ->
                        v.getStatut()
                                == StatutVehicule.DISPONIBLE
                )
                .map(VehiculeMapper::fromVehicule)
                .toList();
    }

    @Override
    public void deleteVehicule(Long id) {

        vehiculeRepository.deleteById(id);
    }
}