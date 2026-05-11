package org.example.gestionlocationbackend;

import org.example.gestionlocationbackend.entity.Agence;
import org.example.gestionlocationbackend.entity.Moto;
import org.example.gestionlocationbackend.entity.Voiture;
import org.example.gestionlocationbackend.enumeartion.BoiteVitesse;
import org.example.gestionlocationbackend.enumeartion.StatutVehicule;
import org.example.gestionlocationbackend.enumeartion.TypeCarburant;
import org.example.gestionlocationbackend.enumeartion.TypeMoto;
import org.example.gestionlocationbackend.repository.AgenceRepository;
import org.example.gestionlocationbackend.repository.VehiculeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GestionLocationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionLocationBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            AgenceRepository agenceRepository,
            VehiculeRepository vehiculeRepository
    ) {

        return args -> {

            Agence agence = Agence.builder()
                    .nom("Agence Casablanca")
                    .adresse("Maarif")
                    .ville("Casablanca")
                    .telephone("0612345678")
                    .build();

            agenceRepository.save(agence);

            Voiture voiture = new Voiture();

            voiture.setMarque("BMW");
            voiture.setModele("X5");
            voiture.setMatricule("123-A-11");
            voiture.setPrixParJour(900);
            voiture.setDateMiseEnService(new Date());
            voiture.setStatut(StatutVehicule.DISPONIBLE);

            voiture.setNombrePortes(4);
            voiture.setTypeCarburant(TypeCarburant.DIESEL);
            voiture.setBoiteVitesse(BoiteVitesse.AUTOMATIQUE);

            voiture.setAgence(agence);

            vehiculeRepository.save(voiture);

            Moto moto = new Moto();

            moto.setMarque("Yamaha");
            moto.setModele("R1");
            moto.setMatricule("456-B-22");
            moto.setPrixParJour(500);
            moto.setDateMiseEnService(new Date());
            moto.setStatut(StatutVehicule.DISPONIBLE);

            moto.setCylindree(1000);
            moto.setTypeMoto(TypeMoto.SPORTIVE);
            moto.setCasqueInclus(true);

            moto.setAgence(agence);

            vehiculeRepository.save(moto);

            System.out.println("Data Loaded Successfully");
        };
    }
}

