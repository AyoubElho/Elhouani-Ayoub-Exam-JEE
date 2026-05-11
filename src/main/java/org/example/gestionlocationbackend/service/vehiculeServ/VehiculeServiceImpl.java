package org.example.gestionlocationbackend.service.vehiculeServ;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.dtos.MotoDTO;
import org.example.gestionlocationbackend.dtos.VehiculeDTO;
import org.example.gestionlocationbackend.dtos.VoitureDTO;
import org.example.gestionlocationbackend.entity.Moto;
import org.example.gestionlocationbackend.entity.Vehicule;
import org.example.gestionlocationbackend.entity.Voiture;
import org.example.gestionlocationbackend.enumeartion.BoiteVitesse;
import org.example.gestionlocationbackend.enumeartion.StatutVehicule;
import org.example.gestionlocationbackend.enumeartion.TypeCarburant;
import org.example.gestionlocationbackend.enumeartion.TypeMoto;
import org.example.gestionlocationbackend.mapper.VehiculeMapper;
import org.example.gestionlocationbackend.repository.VehiculeRepository;
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
    public VoitureDTO saveVoiture(
            VoitureDTO dto
    ) {

        Voiture voiture = new Voiture();

        voiture.setMarque(dto.getMarque());

        voiture.setModele(dto.getModele());

        voiture.setMatricule(
                dto.getMatricule()
        );

        voiture.setPrixParJour(
                dto.getPrixParJour()
        );

        voiture.setDateMiseEnService(
                dto.getDateMiseEnService()
        );

        voiture.setStatut(
                StatutVehicule.valueOf(
                        dto.getStatut()
                )
        );

        voiture.setNombrePortes(
                dto.getNombrePortes()
        );

        voiture.setTypeCarburant(
                TypeCarburant.valueOf(
                        dto.getTypeCarburant()
                )
        );

        voiture.setBoiteVitesse(
                BoiteVitesse.valueOf(
                        dto.getBoiteVitesse()
                )
        );

        Voiture savedVoiture =
                vehiculeRepository.save(
                        voiture
                );

        return (VoitureDTO)
                VehiculeMapper
                        .fromVehicule(
                                savedVoiture
                        );
    }

    @Override
    public MotoDTO saveMoto(
            MotoDTO dto
    ) {

        Moto moto = new Moto();

        moto.setMarque(dto.getMarque());

        moto.setModele(dto.getModele());

        moto.setMatricule(
                dto.getMatricule()
        );

        moto.setPrixParJour(
                dto.getPrixParJour()
        );

        moto.setDateMiseEnService(
                dto.getDateMiseEnService()
        );

        moto.setStatut(
                StatutVehicule.valueOf(
                        dto.getStatut()
                )
        );

        moto.setCylindree(
                dto.getCylindree()
        );

        moto.setTypeMoto(
                TypeMoto.valueOf(
                        dto.getTypeMoto()
                )
        );

        moto.setCasqueInclus(
                dto.isCasqueInclus()
        );

        Moto savedMoto =
                vehiculeRepository.save(
                        moto
                );

        return (MotoDTO)
                VehiculeMapper
                        .fromVehicule(
                                savedMoto
                        );
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