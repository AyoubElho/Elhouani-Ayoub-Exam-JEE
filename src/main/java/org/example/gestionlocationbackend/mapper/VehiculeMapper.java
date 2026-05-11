package org.example.gestionlocationbackend.mapper;

import org.example.gestionlocationbackend.dtos.MotoDTO;
import org.example.gestionlocationbackend.dtos.VehiculeDTO;
import org.example.gestionlocationbackend.dtos.VoitureDTO;
import org.example.gestionlocationbackend.entity.Moto;
import org.example.gestionlocationbackend.entity.Vehicule;
import org.example.gestionlocationbackend.entity.Voiture;

public class VehiculeMapper {

    public static VehiculeDTO fromVehicule(Vehicule vehicule) {

        if (vehicule instanceof Voiture voiture) {

            VoitureDTO dto = new VoitureDTO();

            dto.setId(voiture.getId());
            dto.setMarque(voiture.getMarque());
            dto.setModele(voiture.getModele());
            dto.setMatricule(voiture.getMatricule());
            dto.setPrixParJour(voiture.getPrixParJour());
            dto.setDateMiseEnService(voiture.getDateMiseEnService());

            dto.setStatut(voiture.getStatut().name());

            dto.setTypeVehicule("VOITURE");

            dto.setNombrePortes(voiture.getNombrePortes());

            dto.setTypeCarburant(
                    voiture.getTypeCarburant().name()
            );

            dto.setBoiteVitesse(
                    voiture.getBoiteVitesse().name()
            );

            if (voiture.getAgence() != null) {
                dto.setAgenceId(
                        voiture.getAgence().getId()
                );
            }

            return dto;
        }

        if (vehicule instanceof Moto moto) {

            MotoDTO dto = new MotoDTO();

            dto.setId(moto.getId());
            dto.setMarque(moto.getMarque());
            dto.setModele(moto.getModele());
            dto.setMatricule(moto.getMatricule());
            dto.setPrixParJour(moto.getPrixParJour());
            dto.setDateMiseEnService(
                    moto.getDateMiseEnService()
            );

            dto.setStatut(moto.getStatut().name());

            dto.setTypeVehicule("MOTO");

            dto.setCylindree(moto.getCylindree());

            dto.setTypeMoto(
                    moto.getTypeMoto().name()
            );

            dto.setCasqueInclus(
                    moto.isCasqueInclus()
            );

            if (moto.getAgence() != null) {
                dto.setAgenceId(
                        moto.getAgence().getId()
                );
            }

            return dto;
        }

        return null;
    }
}